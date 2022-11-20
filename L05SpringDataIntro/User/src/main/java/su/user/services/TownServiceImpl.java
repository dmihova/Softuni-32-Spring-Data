package su.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.user.repositories.TownRepository;

@Service
public class TownServiceImpl implements TownService{

    @Autowired
    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }
}
