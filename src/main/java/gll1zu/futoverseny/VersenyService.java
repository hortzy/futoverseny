package gll1zu.futoverseny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VersenyService {
    private final VersenyRepository versenyRepository;
    @Autowired
    public VersenyService(VersenyRepository versenyRepository) {
        this.versenyRepository = versenyRepository;
    }

}
