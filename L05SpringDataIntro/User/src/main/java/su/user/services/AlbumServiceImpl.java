package su.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.user.repositories.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
