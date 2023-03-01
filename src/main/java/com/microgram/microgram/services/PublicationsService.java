package com.microgram.microgram.services;

import com.microgram.microgram.dao.PublicationsDao;
import com.microgram.microgram.dto.PublicationDto;
import com.microgram.microgram.entities.Publication;
import com.microgram.microgram.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationsService {
    private final PublicationsDao publicationsDao;

    public void createPublicationsTable(){
        publicationsDao.createPublicationsTable();
    }
    public void dropPublicationTable(){
        publicationsDao.dropPublicationsTable();
    }
    public Long createNewPublication(PublicationDto publicationDto){
        return publicationsDao.createNewPublication(Publication.builder()
                .image(publicationDto.getImage())
                .description(publicationDto.getDescription())
                .dateTime(LocalDateTime.now())
                .userId(publicationDto.getUserId())
                .build());
    }
    public List<PublicationDto> getPublicationForUser(int userId){
        return publicationsDao.getPublicationsForUser(userId).stream()
                .map(publication -> PublicationDto.builder()
                        .image(publication.getImage())
                        .description(publication.getDescription())
                        .dataTime(publication.getDateTime())
                        .userId(publication.getUserId())
                        .build())
                .collect(Collectors.toList());
    }
    public List<PublicationDto> emptyListOfPublications(){
        return new ArrayList<PublicationDto>();
    }
    public List<PublicationDto> getPublicationsForUserBySubscriptions(int userId){
        return publicationsDao.getPublicationsForUserBySubscriptions(userId).stream()
                .map(publication -> PublicationDto.builder()
                        .image(publication.getImage())
                        .description(publication.getDescription())
                        .dataTime(publication.getDateTime())
                        .userId(publication.getUserId())
                        .build())
                .collect(Collectors.toList());
    }
}
