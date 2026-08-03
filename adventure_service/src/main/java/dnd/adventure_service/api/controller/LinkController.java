package dnd.adventure_service.api.controller;

import dnd.adventure_service.api.dto.CreateLinkDto;
import dnd.adventure_service.mapper.LinkMapper;
import dnd.adventure_service.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;
    private final LinkMapper linkMapper;

    @PostMapping("/links")
    public ResponseEntity<Void> createLink(@RequestBody CreateLinkDto createLinkDto) {
        linkService.createLink(linkMapper.toEntity(createLinkDto));
        return ResponseEntity.ok().build();
    }
}
