package gll1zu.futoverseny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final FutoRepository futoRepository;
    private final VersenyRepository versenyRepository;
    private final EredmenyRepository eredmenyRepository;

    @Autowired
    public DataLoader(FutoRepository futoRepository, VersenyRepository versenyRepository, EredmenyRepository eredmenyRepository) {
        this.futoRepository = futoRepository;
        this.versenyRepository = versenyRepository;
        this.eredmenyRepository = eredmenyRepository;
    }
    @Override
    public void run(String... args) {

        //Futók feltöltése
        FutoEntity futoEntity = new FutoEntity();
        futoEntity.setFutoNev("Tomi");
        futoEntity.setFutoKor(31);
        futoEntity.setFutoNeme("ferfi");

        futoRepository.save(futoEntity);

        FutoEntity futoEntity2 = new FutoEntity();
        futoEntity2.setFutoNev("Emma");
        futoEntity2.setFutoKor(28);
        futoEntity2.setFutoNeme("no");

        futoRepository.save(futoEntity2);

        FutoEntity futoEntity3 = new FutoEntity();
        futoEntity3.setFutoNev("Gabor");
        futoEntity3.setFutoKor(33);
        futoEntity3.setFutoNeme("ferfi");

        futoRepository.save(futoEntity3);

        FutoEntity futoEntity4 = new FutoEntity();
        futoEntity4.setFutoNev("Anna");
        futoEntity4.setFutoKor(27);
        futoEntity4.setFutoNeme("no");

        futoRepository.save(futoEntity4);

        //Versenyek feltöltése
        VersenyEntity versenyEntity = new VersenyEntity();
        versenyEntity.setVersenyNev("Elso");
        versenyEntity.setVersenyId(5);

        versenyRepository.save(versenyEntity);

        VersenyEntity versenyEntity2 = new VersenyEntity();
        versenyEntity2.setVersenyNev("Masodik");
        versenyEntity2.setVersenyId(10);

        versenyRepository.save(versenyEntity2);


        //Eredmények feltöltése
        VersenyEntity keresettVerseny;
        FutoEntity keresettFuto;

        keresettVerseny = versenyRepository.findById(1L).orElse(null);
        keresettFuto = futoRepository.findById(2L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity = new EredmenyEntity();
            eredmenyEntity.setVerseny(keresettVerseny);
            eredmenyEntity.setResztvevo(keresettFuto);
            eredmenyEntity.setEredmenyIdo(48);
            eredmenyRepository.save(eredmenyEntity);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }


        keresettVerseny = versenyRepository.findById(1L).orElse(null);
        keresettFuto = futoRepository.findById(4L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity2 = new EredmenyEntity();
            eredmenyEntity2.setVerseny(keresettVerseny);
            eredmenyEntity2.setResztvevo(keresettFuto);
            eredmenyEntity2.setEredmenyIdo(50);
            eredmenyRepository.save(eredmenyEntity2);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }


        keresettVerseny = versenyRepository.findById(1L).orElse(null);
        keresettFuto = futoRepository.findById(1L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity3 = new EredmenyEntity();
            eredmenyEntity3.setVerseny(keresettVerseny);
            eredmenyEntity3.setResztvevo(keresettFuto);
            eredmenyEntity3.setEredmenyIdo(52);
            eredmenyRepository.save(eredmenyEntity3);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }

        keresettVerseny = versenyRepository.findById(2L).orElse(null);
        keresettFuto = futoRepository.findById(2L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity4 = new EredmenyEntity();
            eredmenyEntity4.setVerseny(keresettVerseny);
            eredmenyEntity4.setResztvevo(keresettFuto);
            eredmenyEntity4.setEredmenyIdo(68);
            eredmenyRepository.save(eredmenyEntity4);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }

        keresettVerseny = versenyRepository.findById(2L).orElse(null);
        keresettFuto = futoRepository.findById(1L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity5 = new EredmenyEntity();
            eredmenyEntity5.setVerseny(keresettVerseny);
            eredmenyEntity5.setResztvevo(keresettFuto);
            eredmenyEntity5.setEredmenyIdo(72);
            eredmenyRepository.save(eredmenyEntity5);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }

        keresettVerseny = versenyRepository.findById(2L).orElse(null);
        keresettFuto = futoRepository.findById(4L).orElse(null);

        if (keresettVerseny != null && keresettFuto != null) {
            EredmenyEntity eredmenyEntity6 = new EredmenyEntity();
            eredmenyEntity6.setVerseny(keresettVerseny);
            eredmenyEntity6.setResztvevo(keresettFuto);
            eredmenyEntity6.setEredmenyIdo(65);
            eredmenyRepository.save(eredmenyEntity6);
        } else {
            System.out.println("Az adott VersenyEntity vagy FutoEntity nem található az adatbázisban.");
        }



    }
}
