import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends Lecturer implements Comparable<Doctor>, Serializable {
    // תכונות
    private ArrayList<String> articles = new ArrayList<>();

    // פעולה בונה
    public Doctor(String lecturerName, String id, String typeOfDegree, double pay) throws InvalidLecturerNameException, InvalidLecturerIdException {
        super(lecturerName, id, typeOfDegree, pay, "DOCTOR");
    }

    protected Doctor(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree) throws InvalidLecturerNameException, InvalidLecturerIdException {
        super(lecturerName, id, typeOfDegree, pay, levelOfDegree);
    }

    // פעולות
    public void addArticle(String article) throws ArticleAlreadyExistsException {
        if (getArticleIndex(article) != -1) {
            throw new ArticleAlreadyExistsException("This article is already in the list.");
        }
        articles.add(article);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Doctor)) return false;
        Doctor other = (Doctor) obj;
        return this.articles.equals(other.articles);
    }

    @Override
    public int compareTo(Doctor other) {
        return Integer.compare(this.articles.size(), other.getArticlesCount());
    }

    // פעולות עזר
    private int getArticleIndex(String articleName) {
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).equalsIgnoreCase(articleName)) {
                return i;
            }
        }
        return -1;
    }

    // get & set
    public ArrayList<String> getArticles() {
        return articles;
    }

    public int getArticlesCount() {
        return articles.size();
    }

    // הדפסה
    @Override
    public String toString() {
        StringBuilder articlesPrint = new StringBuilder();
        for (String article : articles) {
            articlesPrint.append(article).append(", ");
        }

        String finalArticles = (articlesPrint.length() > 0) ?
                articlesPrint.substring(0, articlesPrint.length() - 2) : "No articles yet.";

        return super.toString() + "Articles: [" + finalArticles + "]\n";
    }
}