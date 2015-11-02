

package ep1_hello_word;
import com.dung.ep1.*;


public class EP1_HELLO_WORD {

    public static void main(String[] args) {
            // TODO code application logic here
        System.out.println("hello word");
        filmOBJ film = new filmOBJ("avata","phim vien tuong");
        System.out.println(film.filmName);
        filmOBJ film2 = new filmOBJ();
        System.out.println(film2.filmName);
        SingletonClasss.shareIntance().testFuncShareSingleton();
        FilmExtension ex = new FilmExtension("avata","phim ve nguoei ngoaif","phim vien tuowng");
        ex.printFull();
    }
    
}
