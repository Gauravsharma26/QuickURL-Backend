package com.url.shortener.service;


import com.url.shortener.dtos.ClickEventDTO;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.ClickEvent;
import com.url.shortener.model.UrlMapping;
import com.url.shortener.model.User;
import com.url.shortener.repository.ClickEventRepository;
import com.url.shortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UrlMappingService {

    @Autowired
    ClickEventRepository clickEventRepository;
    @Autowired
    UrlMappingRepository urlMappingRepository;
    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setUser(user);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
return convertToDto(urlMapping);

    }


    private UrlMappingDTO convertToDto(UrlMapping urlMapping) {

        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();

        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());   // Add this line
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());

        return urlMappingDTO;
    }

    private String generateShortUrl() {
        Random random =new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder shortUrl=new StringBuilder(8);
        for(int i=0;i<8;i++){
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
    public List<UrlMappingDTO>getUrlsByUser(User user){
        return urlMappingRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<ClickEventDTO> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping,start,end).stream()
        .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(),Collectors.counting()))
        .entrySet().stream()
        .map(entry->{
            ClickEventDTO clickEventDTO=new ClickEventDTO();
            clickEventDTO.setClickDate(entry.getKey());
            clickEventDTO.setCount(entry.getValue());
            return clickEventDTO;
        })
        .collect(Collectors.toList());

        }
        return null;
    }

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
   List<UrlMapping>urlMappings=urlMappingRepository.findByUser(user);
   List<ClickEvent>clickEvents=clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings,start.atStartOfDay(),end.plusDays(1).atStartOfDay());
   return clickEvents.stream()
           .collect(Collectors.groupingBy(click->click.getClickDate().toLocalDate(),Collectors.counting()));



    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping!=null){
            urlMapping.setClickCount(urlMapping.getClickCount()+1);
            urlMappingRepository.save(urlMapping);


            //Record click event
            ClickEvent clickEvent=new ClickEvent();
            clickEvent.setClickDate(LocalDateTime.now());
            clickEvent.setUrlMapping(urlMapping);
            clickEventRepository.save(clickEvent);
        }
        return  urlMapping;
    }
}
