package com.example;

import org.springframework.web.bind.annotation.*;

/**
 * Created by gschool on 2/25/17.
 */
@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumRepository repository;

    AlbumsController(AlbumRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", ""})
    Iterable<Album> list() {
        return repository.findAll();
    }

    @PostMapping({"/", ""})
    public Album create(@RequestBody Album album) {
        repository.save(album);
        return album;
    }
}
