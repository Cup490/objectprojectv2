public class Doctor extends Lecturer implements Comparable<Doctor> {
    // תכונות
    private String[] articles=new String[1];
    private int articlesCount=0;

    // פעולה בונה
    public Doctor(String lecturerName, String id, String typeOfDegree, double pay){
        super(lecturerName, id, typeOfDegree, pay, "DOCTOR");
    }

    protected Doctor(String lecturerName, String id, String typeOfDegree, double pay, String levelOfDegree){
        super(lecturerName, id, typeOfDegree, pay, levelOfDegree);
    }

    // פעולות
    public void addArticle(String article) throws ArticleAlreadyExistsException {
        if (getArticleIndex(article) != -1) {
            throw new ArticleAlreadyExistsException("This article is already in the list.");
        }
        if (articlesCount == articles.length) {
            doubleArticlesArraySize();
        }
        articles[articlesCount++] = article;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Doctor)) return false;
        Doctor other = (Doctor) obj;
        if (this.articlesCount != other.articlesCount) return false;
        for (int i = 0; i < this.articlesCount; i++) {
            if (!this.articles[i].equalsIgnoreCase(other.articles[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Doctor other) {
        return Integer.compare(this.articlesCount, other.getArticlesCount());
    }

    // פעולות עזר
    private int getArticleIndex(String articleName) {
        for (int i = 0; i < articlesCount; i++) {
            if (articles[i].equalsIgnoreCase(articleName)) {
                return i;
            }
        }
        return -1;
    }

    private void doubleArticlesArraySize() {
        String[] newArticles = new String[articles.length * 2];
        for (int i = 0; i < articlesCount; i++) {
            newArticles[i] = articles[i];
        }
        articles = newArticles;
    }

    // get & set
    public String[] getArticles() {
        return articles;
    }

    public int getArticlesCount(){
        return articlesCount;
    }

    // אין פעולות set עבור המערכים מכיוון שהם יכולים להתעדכן רק דרך פעולות ולא על ידי המשתמש
    // אין פעולת set למשתנים הסופרים מכיוון שהם מתעדכנים אוטומטית ע"י הפעולות ושינוי ישיר שלו עלול ליצור חוסר התאמה בין הספירה לתוכן המערך בפועל

    // הדפסה
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