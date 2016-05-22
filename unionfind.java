/**
 * Created by Karan on 5/22/16.
 */
/* interface for generic UF problem (dynamic connectivity problem) */
public interface unionfind{
    void union(int p, int q);
    int find(int p);
    boolean connected(int p, int q);
    int count();
}
