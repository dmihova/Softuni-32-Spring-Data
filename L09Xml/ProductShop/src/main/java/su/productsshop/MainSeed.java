package su.productsshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.productsshop.service.SeedService;

//@Component
public class MainSeed implements CommandLineRunner {
    @Autowired
    private SeedService seedService;

    public MainSeed(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {

        seedService.seedAll();

    }
}
