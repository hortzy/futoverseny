package gll1zu.futoverseny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutoService {

    private final FutoRepository futoRepository;
    @Autowired
    public FutoService(FutoRepository futoRepository) {
        this.futoRepository = futoRepository;
    }

}
