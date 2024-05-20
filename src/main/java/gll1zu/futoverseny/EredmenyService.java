package gll1zu.futoverseny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EredmenyService {
    private final EredmenyRepository eredmenyRepository;
    @Autowired
    public EredmenyService(EredmenyRepository eredmenyRepository) {
        this.eredmenyRepository = eredmenyRepository;
    }

}
