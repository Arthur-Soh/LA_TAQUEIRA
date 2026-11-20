import java.util.List;
public interface Article {
    String getNom();
    double getPrix();
    List<String> getDetails();
    Categorie getCategorie();
    Article copier();

    }


