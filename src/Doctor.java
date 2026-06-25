public class Doctor extends Lecturer {
    private String[] articles=new String[1];
    private int articlesCount=0;

    public Doctor(String lecturerName, String id, String typeOfDegree, double pay){
        super(lecturerName, id, typeOfDegree, pay, "DOCTOR");
    }

    protected Doctor(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree){
        super(lecturerName, id, typeOfDegree, pay, levelOfDegree);
    }

    private int getArticleIndex(String articleName) {
        for (int i = 0; i < articlesCount; i++) {
            if (articles[i].equalsIgnoreCase(articleName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean addArticle(String article) {
        if (getArticleIndex(article) != -1) {
            return false;
        }
        if (articlesCount == articles.length) {
            doubleArticlesArraySize();
        }
        articles[articlesCount++] = article;
        return true;
    }

    private void doubleArticlesArraySize() {
        String[] newArticles = new String[articles.length * 2];
        for (int i = 0; i < articlesCount; i++) {
            newArticles[i] = articles[i];
        }
        articles = newArticles;
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        StringBuilder articlesPrint = new StringBuilder();
        for (int i = 0; i < articlesCount; i++) {
            articlesPrint.append(articles[i]).append(", ");
        }

        String finalArticles = (articlesPrint.length() > 0) ?
                articlesPrint.substring(0, articlesPrint.length() - 2) : "No articles yet.";

        return super.toString() + "Articles: [" + finalArticles + "]\n";
    }
}
