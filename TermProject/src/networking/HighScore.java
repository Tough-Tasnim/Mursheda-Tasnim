package networking;

public class HighScore {
    public  String name;
    public  int score;
    public HighScore(String name,int score) {
        this.name = name;
        this.score = score;
    }
    public String getName(){
        return name;
    }
    public int getScore(){return score;}
    public void setName(String name){
        this.name=name;
    }
    public void setScore(int score){this.score=score;}
}
