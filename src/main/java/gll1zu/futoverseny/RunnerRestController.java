package gll1zu.futoverseny;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@RestController
@RequestMapping("/api/runners")

public class RunnerRestController {
    private final FutoRepository futoRepository;
    private final VersenyRepository versenyRepository;
    private final EredmenyRepository eredmenyRepository;

    @Autowired
    public RunnerRestController(FutoRepository futoRepository, VersenyRepository versenyRepository,EredmenyRepository eredmenyRepository) {
        this.futoRepository = futoRepository;
        this.versenyRepository = versenyRepository;
        this.eredmenyRepository = eredmenyRepository;
    }


    @GetMapping("/getRunners")
    public List<FutoEntity> getAllRunners() {
        return futoRepository.findAll();
    }

    @GetMapping("/getRaceRunners/{versenyId}")
    public ResponseEntity<List<EredmenyEntity>> getRaceRunners(@PathVariable("versenyId") Long versenyId) {
        if (!versenyRepository.existsById(versenyId)) {
            return ResponseEntity.notFound().build();
        }

        // Versenyhez tartozó összes futó eredményeinek lekérdezése
        List<EredmenyEntity> eredmenyek = eredmenyRepository.findByVersenyId(versenyId);

// Az eredményeket futó neve alapján rendezzük emelkedő sorrendben
        List<EredmenyEntity> rendezettEredmenyek = eredmenyek.stream()
                .sorted(Comparator.comparing(eredmeny -> eredmeny.getResztvevo().getFutoNev()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(rendezettEredmenyek);


    }

    @GetMapping("/getAverageTime/{versenyId}")
    public ResponseEntity<Double> getAverageTime(@PathVariable("versenyId") Long versenyId) {
        // Ellenőrizzük, hogy létezik-e az adott ID-vel verseny
        if (!versenyRepository.existsById(versenyId)) {
            return ResponseEntity.notFound().build();
        }

        // Az adott versenyhez tartozó összes eredmény lekérdezése
        List<EredmenyEntity> eredmenyek = eredmenyRepository.findByVersenyId(versenyId);

        // Számítsuk ki az átlagos időt az összes eredmény alapján
        double osszIdo = 0;
        int count = 0;
        for (EredmenyEntity eredmeny : eredmenyek) {
            osszIdo += eredmeny.getEredmenyIdo();
            count++;
        }

        if (count == 0) {
            return ResponseEntity.ok(0.0); // Ha nincs eredmény, az átlagos idő 0
        }

        double averageTime = osszIdo / count;
        return ResponseEntity.ok(averageTime);
    }

    @GetMapping("/versenyek")
    public String listVersenyek() {
        List<VersenyEntity> versenyek = versenyRepository.findAll();
        return "versenyek";
    }



    @PostMapping("/addRunner")
    public ResponseEntity<String> addRunner( @RequestBody FutoRequest futoRequest) {
        FutoEntity futo = new FutoEntity();
        futo.setFutoNev(futoRequest.getFutoNev());
        futo.setFutoKor(futoRequest.getFutoKor());
        futo.setFutoNeme(futoRequest.getFutoNeme());

        futoRepository.save(futo);

        return ResponseEntity.status(HttpStatus.CREATED).body("A futó sikeresen hozzáadva, a következő ID-val: " + futo.getFutoId());
    }

    @PostMapping("/updateRace")
    public ResponseEntity<String> updateRace(@RequestBody UpdateRaceRequest request) {
        // Lekérjük a versenyt az azonosító alapján
        Optional<VersenyEntity> optionalVerseny = versenyRepository.findById(request.getVersenyId());
        if (optionalVerseny.isPresent()) {
            VersenyEntity verseny = optionalVerseny.get();
            // Frissítjük a versenyt
            verseny.setVersenyNev(request.getVersenyNev());
            verseny.setVersenyTav(request.getVersenyTav());
            versenyRepository.save(verseny);
            return ResponseEntity.ok("A verseny sikeresen frissítve.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addResult")
    public ResponseEntity<?> addResult(@RequestBody AddResultRequest request) {
        // Ellenőrizzük, hogy a megadott verseny- és résztvevő azonosítók érvényesek-e
        Optional<VersenyEntity> optionalVerseny = versenyRepository.findById(request.getVersenyId());
        Optional<FutoEntity> optionalFuto = futoRepository.findById(request.getResztvevo());

        if (optionalVerseny.isPresent() && optionalFuto.isPresent()) {
            // Hozz létre egy új eredmény objektumot és mentsd el az adatbázisban
            EredmenyEntity eredmeny = new EredmenyEntity();
            eredmeny.setVerseny(optionalVerseny.get());
            eredmeny.setResztvevo(optionalFuto.get());
            eredmeny.setEredmenyIdo(request.getEredmenyIdo());
            eredmenyRepository.save(eredmeny);

            return ResponseEntity.status(HttpStatus.CREATED).body("Az eredmény sikeresen rögzítve az azonosítóval: " + eredmeny.getEredmenyId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A megadott verseny vagy résztvevő nem található.");
        }
    }

    @PostMapping("/addRace")
    public ResponseEntity<String> addRace(@RequestBody NewRaceRequest request) {
        VersenyEntity verseny = new VersenyEntity();
        verseny.setVersenyNev(request.getVersenyNev());
        verseny.setVersenyTav(request.getVersenyTav());
        versenyRepository.save(verseny);
        return ResponseEntity.status(HttpStatus.CREATED).body("Az új verseny sikeresen létrehozva azonosítóval: " + verseny.getVersenyId());
    }


    public static class FutoRequest {
        private String futoNev;
        private int futoKor;
        private String futoNeme;

        public String getFutoNev() {
            return futoNev;
        }

        public void setFutoNev(String futoNev) {
            this.futoNev = futoNev;
        }

        public int getFutoKor() {
            return futoKor;
        }

        public void setFutoKor(int futoKor) {
            this.futoKor = futoKor;
        }

        public String getFutoNeme() {
            return futoNeme;
        }
        public void setFutoNeme(String futoNeme) {
            this.futoNeme = futoNeme;
        }
    }

    public static class UpdateRaceRequest {
        private long versenyId;
        private String versenyNev;
        private int versenyTav;

        public long getVersenyId() {
            return versenyId;
        }

        public void setVersenyId(long versenyId) {
            this.versenyId = versenyId;
        }

        public String getVersenyNev() {
            return versenyNev;
        }

        public void setVersenyNev(String versenyNev) {
            this.versenyNev = versenyNev;
        }

        public int getVersenyTav() {
            return versenyTav;
        }

        public void setVersenyTav(int versenyTav) {
            this.versenyTav = versenyTav;
        }
    }


    public static class AddResultRequest {
        private long versenyId;
        private long resztvevo;
        private int eredmenyIdo;

        public long getVersenyId() {
            return versenyId;
        }

        public void setVersenyId(Long versenyId) {
            this.versenyId = versenyId;
        }

        public long getResztvevo() {
            return resztvevo;
        }

        public void setResztvevo(long resztvevo) {
            this.resztvevo = resztvevo;
        }

        public int getEredmenyIdo() {
            return eredmenyIdo;
        }

        public void setEredmenyIdo(int eredmenyIdo) {
            this.eredmenyIdo = eredmenyIdo;
        }
    }

    public static class NewRaceRequest {
        private String versenyNev;
        private int versenyTav;

        public String getVersenyNev() {
            return versenyNev;
        }

        public void setVersenyNev(String versenyNev) {
            this.versenyNev = versenyNev;
        }

        public int getVersenyTav() {
            return versenyTav;
        }

        public void setVersenyTav(int versenyTav) {
            this.versenyTav = versenyTav;
        }
    }

}
