package cse214homework7;

import java.io.*;
import java.util.*;

/**
* The WebGraph class organizes the WebPage objects as a directed graph.
* 
* Name: Jonathan Zhou(Rongruo Zhou)
* SBU ID: 111585434  
* Email Address: rongruo.zhou@stonybrook.edu
* Programming assignment number: Homework 7
* Course: CSE214 - 02
* Recitation: R11
* Recitation TA's Name: Reed Gantz
* Grading TA's Name: Md Naimul Hoque
**/
public class WebGraph{
    public static final int MAX_PAGES = 40;
    private WebPage[] pages;
    private int[][] edges = new int[40][40];
    
    /**
    * Returns an instance of WebGraph with no input. 
    *
    * Postcondition:
    *   A WebGraph object with no input value is created.
    *       All the variables are set to their default value.
    **/
    public WebGraph(){
    }
    
    /**
    * Returns an instance of WebGraph object with specific inputs. 
    *
    * @param num
    *   The input int value that indicates the length of pages of this WebGraph.
    * 
    * Postcondition:
    *   A WebGraph object with num is created.
    **/
    public WebGraph(int num){
        pages = new WebPage[num];
    }
    
    /**
    * Returns a reference to the pages of WebGraph object.
    *
    * Precondition:
    *   A WebGraph object has been created.
    *  
    * @return 
    *   A reference of pages of the WebGraph object.
    **/
    public WebPage[] getPages(){
        return pages;
    }
    
    /**
    * Returns a reference to the edges of WebGraph object.
    *
    * Precondition:
    *   A WebGraph object has been created.
    *  
    * @return 
    *   A reference of edges of the WebGraph object.
    **/
    public int[][] getEdges(){
        return edges;
    }
    
    /**
    * Returns an instance of WebGraph object with specific inputs from files.
    *
    * @param pagesFile
    *   The input String value that indicates the pages of this WebGraph.
    * @param linksFile
    *   The input String value that indicates the edges of this WebGraph.
    * 
    * @throws FileNotFoundException
    * @throws IllegalArgumentException
    * 
    * Postcondition:
    *   A WebGraph object in created based on the information in input pagesFile
    *       and linksFile.
    **/
    public static WebGraph buildFromFiles(String pagesFile,String linksFile) 
            throws IllegalArgumentException, FileNotFoundException{
        File page = new File(pagesFile);
        Scanner test = new Scanner(page);
        int line = 0 ;
        while(test.hasNextLine()){
            line++;
            test.nextLine();
        }
        test.close();
        WebGraph newWeb = new WebGraph(line);
        for(int w = 0 ; w < 40 ; w++){
            for(int e = 0 ; e < 40 ; e++){
                newWeb.edges[w][e] = -1;
            }
        }
        Scanner sc = new Scanner(page);
        int num = 1;
        while(sc.hasNextLine()){
            String current = sc.nextLine();
            for(int x = 0 ; x < current.length(); x++){
                if(current.charAt(x) != ' '){
                    current = current.substring(x,current.length());
                    break;
                }
            }
            String url = current.substring(0,current.indexOf(' '));
            current = current.substring(current.indexOf(' ') + 1, 
                    current.length());
            String [] keys = current.split(" ");
            WebPage newpage = new WebPage(url,num,num,keys);
            newWeb.pages[num-1] = newpage;
            num++;
        }
        sc.close();
        for(int p = 0 ; p < newWeb.pages.length ; p++){
            for(int o = 0 ; o < newWeb.pages.length ; o++){
                newWeb.edges[p][o] = 0;
            }
        }
        File link = new File(linksFile);
        Scanner sd = new Scanner(link);
        while(sd.hasNextLine()){
            String now = sd.nextLine();
            while(now.charAt(0) == ' '){
            now = now.substring(1, now.length());
        }
            String [] cur = now.split(" ");
            now = cur[0];
            String next = cur[1];
            for(int z = 0 ; z < newWeb.pages.length; z++){
                if(now.equals(newWeb.pages[z].getUrl())){
                    for(int q = 0 ; q < newWeb.pages.length; q++){
                        if(newWeb.pages[q].getUrl().equals(next)){
                            newWeb.edges[z][q] = 1;
                            break;
                        }
                    }
                }
            }
        }
        sd.close();
        return newWeb;
    }
    
    /**
    *  Add a new WebPage into the WebGraph.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The new input WebPage is added into the WebGraph.
    * 
    * @param url
    *   The url of the new WebPage.
    * @param keywords
    *   The keywords of the new WebPage.
    * 
    * @throws IllegalArgumentException
    **/
    public void addPage(String url,String[]keywords) throws 
            IllegalArgumentException{
        if(url == null || keywords == null){
            throw new IllegalArgumentException("Url and keywords cannot be "
                    + "null!");
        }
        for(int x = 0 ; x < pages.length ; x++){
            if(pages[x].getUrl().equals(url)){
                throw new IllegalArgumentException("This url already exists "
                        + "in the WebGraph!");
            }
        }
        WebPage[] cur = new WebPage[pages.length+1];
        for(int p = 0 ; p < pages.length ; p++){
            cur[p] = pages[p];
        }
        pages = cur;
        WebPage newPage = new WebPage(url,pages.length,pages.length,keywords);
        pages[pages.length-1] = newPage;
        for(int y = 0 ; y < pages.length -1 ; y++){
            edges[y][pages.length-1] = 0;
            edges[pages.length-1][y] = 0;
        }
        edges[pages.length-1][pages.length-1] = 0;
    }
    
    /**
    *  Add a new link between two WebPages.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   Two WebPages are linked based on the input information.
    * 
    * @param source
    *   The name of the source WebPage.
    * @param destination
    *   The name of the destination WebPage.
    * 
    * @throws IllegalArgumentException
    **/
    public void addLink(String source,String destination) throws 
            IllegalArgumentException{
        if(source == null || destination == null){
            throw new IllegalArgumentException("Source and "
                    + "destination cannot be null!");
        }
        boolean sourceFound = false;
        boolean destinationFound = false;
        int sourceMark = -1;
        int destinationMark = -1;
        for(int x = 0 ; x < pages.length ; x++){
            if(pages[x].getUrl().equals(source)){
                sourceMark = x;
                sourceFound = true;
            }
            else if(pages[x].getUrl().equals(destination)){
                destinationMark = x;
                destinationFound = true;
            }
        }
        if(sourceFound == false || destinationFound == false){
            throw new IllegalArgumentException("Source or destination "
                    + "cannot be found!");
        }
        else{
            edges[sourceMark][destinationMark] = 1;
        }
    }
    
    /**
    *  Remove the WebPage from the WebGraph based on the input url.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The WebPage with input url is removed the WebGraph.
    * 
    * @param url
    *   The url of the WebPage.
    **/
    public void removePage(String url){
        if(url == null){
            throw new IllegalArgumentException("Url and keywords cannot "
                    + "be null!");
        }
        boolean urlFound = false;
        int mark = -1;
        boolean end = false;
        for(int x = 0 ; x < pages.length ; x++){
            if(pages[x].getUrl().equals(url)){
                urlFound = true;
                mark = x;
                if(x == pages.length-1){
                    WebPage [] sor = new WebPage [pages.length-1];
                    for(int a = 0 ; a < sor.length ; a++){
                        sor[a] = pages[a];
                    }
                    pages = sor;
                    end = true;
                }
                else{
                    for(int y = x ; y < pages.length-1 ; y++){
                        pages[y] = pages[y+1];
                        pages[y].setIndex(pages[y].getIndex()-1);
                    }
                    WebPage [] ptr = new WebPage[pages.length-1];
                    for(int s = 0 ; s < ptr.length ; s++){
                        ptr[s] = pages[s];
                    }
                    pages = ptr;
                    break;
                }
            }
        }
        if(end == true){
            for(int p = 0 ; p < pages.length+1 ; p++){
                edges[mark][p] = -1;
                edges[p][mark] = -1;
            }
        }
        else{
            for(int y = 0 ; y < pages.length ; y++){
                if(y == mark){
                    for(int s = 0 ; s < pages.length+1 ; s++){
                        for(int v = mark ; v < pages.length ; v++){
                        edges[v][s] = edges[v+1][s];
                        }
                        edges[pages.length][s] = -1;
                    }
                }
                else{
                    for(int l = mark ; l < pages.length  ; l++){
                        edges[y][l] = edges[y][l+1];  
                    }
                    edges[y][pages.length] = -1;
                }
            }
        }
        if(urlFound == false){
            throw new IllegalArgumentException("Url cannot be found!");
        }
    }
    
    /**
    *  Remove the link between two WebPages.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The link between two WebPages is revmoed.
    * 
    * @param source
    *   The name of the source WebPage.
    * @param destination
    *   The name of the destination WebPage.
    * 
    * @throws IllegalArgumentException
    **/
    public void removeLink(String source, String destination) throws 
            IllegalArgumentException{
        if(source == null || destination == null){
            throw new IllegalArgumentException("Source and destination"
                    + " cannot be null!");
        }
        boolean sourceFound = false;
        boolean destinationFound = false;
        int sourceMark = -1;
        int destinationMark = -1;
        for(int x = 0 ; x < pages.length ; x++){
            if(pages[x].getUrl().equals(source)){
                sourceMark = x;
                sourceFound = true;
            }
            else if(pages[x].getUrl().equals(destination)){
                destinationMark = x;
                destinationFound = true;
            }
        }
        if(sourceFound == false || destinationFound == false){
            throw new IllegalArgumentException("Source or destination c"
                    + "annot be found!");
        }
        else{
            edges[sourceMark][destinationMark] = 0;
        }
    }
    
    /**
    *  Update the rank of WebPages in this WebGraph based on edges.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The rank of WebPages is updated.
    **/
    public void updatePageRanks(){
        for(int x = 0 ; x < pages.length ; x++){
            int count = 0;
            for(int y = 0 ; y < pages.length ; y++){
                if(edges[y][x] == 1){
                    count ++;
                }
            }
            pages[x].setRank(count);
        }
    }
    
    /**
    *  Set the links of all WebPages in this WebGraph.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The links of all WebPages is updated.
    **/
    public void links(){
        for(int x = 0 ; x < pages.length ; x++){
            ArrayList<Integer> count = new ArrayList<>();
            for(int y = 0 ; y < pages.length ; y++){
                if(edges[x][y] == 1){
                    count.add(y);
                }
            }
            if(count.size() > 0){
                int [] fin = new int[count.size()];
                for(int z = 0 ; z < count.size() ; z++){
                    fin[z] = count.get(z);
                }
                pages[x].setLinks(fin);
            }
            else{
                pages[x].setLinks(null);
            }
        }
    }
    
    /**
    *  Search for a specific WebPage and print it.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   All the WebPages with the input keywords are found and printed,
    *       if not, an error message is printed.
    * 
    * @param key
    *   One of the keywords of the target WebPage.
    **/
    public void search(String key){
        boolean found = false;
        int num = 0;
        ArrayList<WebPage> con = new ArrayList<>();
        ArrayList<Integer> cont = new ArrayList<>();
        for(int x = 0 ; x < pages.length ; x++){
            String [] current = pages[x].getKeywords();
            for(int y = 0 ; y < current.length ; y++){
                if(current[y].equals(key)){
                    con.add(pages[x]);
                    cont.add(pages[x].getRank());
                    num++;
                    found = true;
                    break;
                }
            }      
        }
        if(found == false){
            System.out.println("Cannot find any url with input keyword");
        }
        else{
            for(int q = 0 ; q < cont.size() -1 ; q++){
                for(int p = q ; p < cont.size() ; p++){
                    if(cont.get(p) > cont.get(q)){
                        int now = cont.get(q);
                        WebPage no = con.get(q);
                        cont.set(q, cont.get(p));
                        cont.set(p,now);
                        con.set(q,con.get(p));
                        con.set(p, no);
                    }
                }
            }
            System.out.println("Rank   PageRank    URL");
            System.out.println("---------------------------------------------");
            for(int w = 0 ; w < con.size() ; w++){
                String str = "";
                str = String.format("%-2s%-4d%-5s%-5d%-2s%-2s","  ",w+1,"|",
                        cont.get(w),"|",con.get(w).getUrl());
                System.out.println(str);
            }
        }
    }
    
    /**
    *  Sort the pages based on their index.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The pages are sorted based on their index.
    **/
    public void indexSort(){
        IndexComparator c1 = new IndexComparator();
        for(int x = 0 ; x < pages.length -1 ; x++){
            for(int y = x ; y < pages.length ; y++){
                int an = c1.compare(pages[x], pages[y]);
                if(an == 1){
                   WebPage ptr = pages[x];
                   pages[x] = pages[y];
                   pages[y] = ptr;
                }
            }
        }
    }
    
    /**
    *  Sort the pages based on their url.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The pages are sorted based on their url.
    **/
    public void urlSort(){
        URLComparator u1 = new URLComparator();
        for(int x = 0 ; x < pages.length -1 ; x++){
            for(int y = x ; y < pages.length ; y++){
                int an = u1.compare(pages[x], pages[y]);
                if(an == 1){
                   WebPage ptr = pages[x];
                   pages[x] = pages[y];
                   pages[y] = ptr;
                }
            }
        }
    }
    
    /**
    *  Sort the pages based on their rank.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   The pages are sorted based on their rank.
    **/
    public void rankSort(){
        RankComparator r1= new RankComparator();
        for(int x = 0 ; x < pages.length -1 ; x++){
            for(int y = x ; y < pages.length ; y++){
                int an = r1.compare(pages[x], pages[y]);
                if(an == -1){
                   WebPage ptr = pages[x];
                   pages[x] = pages[y];
                   pages[y] = ptr;
                }
            }
        }
    }
    
    /**
    *  Print all the WebPages in this WebGraph.
    *
    * Precondition:
    *   A WebGraph Object was initialized.
    *  
    * Postcondition:
    *   ALl the WebPages in this WebGraph is printed in tabular.
    **/
    public void printTable(){
        System.out.println("Index     URL               PageRank  Links      "
                + "         Keywords");
        System.out.println("-------------------------------------------------"
                + "-----------------------------------------------------");
        for(int x = 0 ; x < pages.length; x++){
            System.out.println(pages[x].toString());
        }
    }
}