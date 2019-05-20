package Genetic_Algorithm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ColorStateGenetic extends JPanel {
    public Rectangle[] rectangle;
    public Color[] colors;
    public Rectangle[] rectangle1;
    public Color[] colors1;


    public ColorStateGenetic() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setTitle("Color State HillClimbing");
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rectangle = krijoDrejtkendshat();
        colors = new Color[rectangle.length];
        for (int i = 0; i < rectangle.length; i++) {
            colors[i] = ktheNgjyre();
        }


    }
    public Color [] geneticAlgorithm() //implementon alg.gjenetik ne ate menyre qe nje here behet crossover-i e tani behet 
                                       // zgjedhja e me te mireve. 
    {
     Color [][] ngjyrat = krijoIndividet(100);
     Color [][] rez = new Color[50][100];
     
     for(int i =0;i<50;i++)
       rez[i]=crossover(ngjyrat[i],ngjyrat[i+50]);
     
     System.out.print("Pas crossoverit te pare :");
     for(Color []  c : rez)
     System.out.print(" "+numeroKonfliktet(c));
     System.out.println();
       
     rez = fitness(rez,10);   
     System.out.print("Dhjete me te mirat :");
     for(Color []  c : rez)
     System.out.print(" "+numeroKonfliktet(c));
     System.out.println();
     
     Color [][] rez1 = new Color[5][100];
     for(int i = 0;i<5;i++)
      rez1[i] = crossover(rez[i],rez[i+5]);
      
      int r = (int)(Math.random()*4.999999);
      rez1[r] = mutacion(rez1[r]);
        
     System.out.print("Pas crossoverit te dyte :");
     for(Color []  c : rez1)
     System.out.print(" "+numeroKonfliktet(c));
     System.out.println();
      
     rez1 = fitness(rez1,2);
     
     System.out.print("Dy me te mirat :"+numeroKonfliktet(rez1[0])+" "+numeroKonfliktet(rez1[1]));
     System.out.println();
     
     Color [] rez2 = crossover(rez1[0],rez1[1]);
     System.out.println("Numri i konfliktev : "+numeroKonfliktet(rez2));  
     System.out.println();
    
     return rez2; 
    }
    public Color [] geneticAlgorithm2() // implementon alg.gjenetik ne ate menyre qe nje here te behet zgjedhja e me te
                                        //  mireve e pastaj behet crossoveri.
    {
      Color [][] ngjyrat = krijoIndividet(100);
      ngjyrat = fitness(ngjyrat,10);
     
      System.out.println();
       
      System.out.print("Dhjete rastet me te mira nga njeqind : ");
      for(Color [] c : ngjyrat )
       System.out.print(numeroKonfliktet(c)+" ");
       
      System.out.println();
          
      Color [] [] rez = new Color[5][100];
      
      for(int i = 0;i<5;i++)
       rez[i]=crossover(ngjyrat[i],ngjyrat[i+5]);
      
      System.out.print("Pas crossover-it : ");
      for(Color [] c : rez )
       System.out.print(numeroKonfliktet(c)+" ");
      
      System.out.println();
             
      int r = (int)(Math.random()*4.999999); 
      rez[r] = mutacion(rez[r]);   
      
      System.out.print("Pas mutacionit te njerit nga pese individeve : "); 
      for(Color [] c :rez )
       System.out.print(numeroKonfliktet(c)+" ");
        
      System.out.println();
      
      rez = fitness(rez,2);
      
      System.out.print("Numri i konfliktev per dy rastet e fundit : ");
      for(Color [] c : rez)
       System.out.print(numeroKonfliktet(c)+" ");
      
      System.out.println();
        
      Color [] rez2 = new Color[100];
      rez2 = crossover(rez[0],rez[1]);
      
      
      System.out.println("Numri perfundimtare i konfliktev : "+numeroKonfliktet(rez2));
    
    return rez2;
    }
    public Color [] perfundo (int n) // ekzekuton n-here algoritmin gjenetik dhe merr rezultatet me te mira te cilat i perdor 
                                     // si hyrje edhe nje here per alg.gjenetik
    {
     
     Color [][] ngjyrat = new Color[n][100];
     for(int i = 0;i<n;i++)
      ngjyrat[i]=geneticAlgorithm();
       System.out.println();
       System.out.print(n+" rastet me te mira : ");
      for(Color [] c : ngjyrat)
       System.out.print(" "+ numeroKonfliktet(c));
       
       System.out.println();

     ngjyrat = fitness(ngjyrat,6);
     Color[] [] rez1 = new Color [3][100];
     for(int i = 0;i<3;i++)
      rez1[i]=crossover(ngjyrat[i],ngjyrat[i+3]);
     
     rez1 = fitness(rez1,2);
     Color [] rez = crossover(rez1[0],rez1[1]);
     
  
 
     System.out.println();
     System.out.println("Perfundimi pas "+n+" gjenerimev te algoritmit : "+numeroKonfliktet(rez));
     System.out.println();
     
     return rez;    
    }
   
    public Color [] crossover(Color [] a, Color [] b)//bene kryqezimin e dy vargjev te ngjyrave duke marre gjysen e te parit 
                                                     // dhe gjysen e te dytit
    {
      Color rez [] = new  Color [100];
      for (int i = 0; i <100 ; i++)
      {
         if(i<50)
          rez[i]=a[i];
    
         else
          rez[i]=b[i];
          
       }
        
      
      return rez;
   }
   public Color[] mutacion (Color [] color) //bene mutacion nje individe ne ate menyre qe gjenet vetem te permirsohen
   {
    for(int i = 0;i<20;i++)
     {int r = (int)(Math.random()*99.999999);
     if(gjejKonfliktet(r,color))
     color[r] =ktheNgjyre();
     } 
    return color;   
   }
   
    public Color [][] fitness (Color [][] colors,int m)//zgjedh m-individet me te mire
    {
     int n = colors.length;
     Color [][] rez= new Color [n][100];
     Color [] temp = new Color[100];
     for(int i = 0;i<n;i++)
     {
      
      rez [i]=colors[i];
      for(int j = i;j>0;j--)
      if(numeroKonfliktet(rez[j])<numeroKonfliktet(rez[j-1]))
      {
       temp = rez[j];
       rez[j]=rez[j-1];
       rez[j-1]=temp;
      }     
     }
     Color [][] rez1 = new Color [m][100];
     for(int i = 0;i<m;i++)
      rez1[i] =rez[i];
     return rez1;
    }
    
    public Color[][] krijoIndividet (int n)// gjeneron individet ne menyre te rastesishme
    {
     Color [][] rez = new Color[n][100];
     for(int i = 0;i<n;i++)
      for(int j =0 ;j< 100;j++)
       rez[i][j] = ktheNgjyre();
     
     return rez;  
    }

    public void jepVlere() {
        rectangle1 = new Rectangle[rectangle.length];
        colors1 = new Color[colors.length];

        for (int i = 0; i < rectangle.length; i++)
            rectangle1[i] = rectangle[i];
        for (int i = 0; i < colors.length; i++)
            colors1[i] = colors[i];


    }

    public Color ktheNgjyre() {
        Color[] vargu = new Color[4];
        vargu[0] = Color.red;
        vargu[1] = Color.blue;
        vargu[2] = Color.GREEN;
        vargu[3] = Color.ORANGE;

        return vargu[(int) (Math.random() * 3.9999999999999)];
    }

    public void draw(Graphics g, Rectangle r, Color c) {
        g.setColor(c);
        g.fillRect((int) r.getX(), (int) r.getY(), r.width, r.height);
        g.setColor(Color.black);
        g.drawRect((int) r.getX(), (int) r.getY(), r.width, r.height);
    }


    public void vizato(Graphics g) {
        for (int i = 0; i < rectangle.length; i++) {
            draw(g, rectangle[i], colors[i]);
        }
    }


    public Rectangle[] krijoDrejtkendshat() {
        Rectangle[] v = new Rectangle[100];
        int x = 0, y = 0, d = 70;
        for (int i = 0; i < 10; i++) {
            int s = 100;
            x += d + (int) (Math.random() * 20);
            y = 0;
            for (int j = 0; j < 10; j++) {
                //  int d1 = (int)(Math.pow(-1,(int)(Math.random()*2)))*10;
                //   x += d1;
                y += d;//+ (int) (Math.random() * 20);
                v[i * 10 + j] = new Rectangle(x, y, s, s);
            }
        }
        return v;
    }

    public boolean kontrolloFqinjet(Rectangle a, Rectangle b) {        // True nese kufizohen, false nese jo
        boolean answer = false;
        int x = (int) a.getX();
        int y = (int) a.getY();
        int s = a.getSize().height;

        int x1 = (int) b.getX();
        int y1 = (int) b.getY();

        if (Math.abs(x - x1) <= s && Math.abs(y - y1) <= s)
            answer = true;


        return answer;
    }


    public void paintComponent(Graphics g) {
        vizato(g);
    }

    public boolean gjejKonfliktet(int x) { //metoda kthen true nese ka konflikt
        boolean answer = false;
        ArrayList<Integer> lista = gjejKufizat(x);
        for (int a : lista) {
            if (colors[a].equals(colors[x])) {
                answer = true;
            }
        }
        return answer;
    }
     public boolean gjejKonfliktet(int x,Color [] colors) { //metoda kthen true nese ka konflikt
        boolean answer = false;
        ArrayList<Integer> lista = gjejKufizat(x);
        for (int a : lista) {
            if (colors[a].equals(colors[x])) {
                answer = true;
            }
        }
        return answer;
    }


    public ArrayList<Integer> gjejKufizat(int x) {
        ArrayList<Integer> rez = new ArrayList<>();
        for (int i = 0; i < rectangle.length; i++) {
            if (i == x) {
                continue;
            }
            if (kontrolloFqinjet(rectangle[x], rectangle[i])) {
                rez.add(i);
            }
        }
        return rez;
    }

    public void hillClimbing(int x) throws InterruptedException {
    //    System.out.print("Ndodhet ne " + x + "  ");
        ArrayList<Rectangle> vargu = new ArrayList<>();

        for (Rectangle r1 : rectangle)
            vargu.add(r1);
        Fillimi:
        for (int i = 0; i < 1000; i++) {
            int count = 0;
            while (gjejKonfliktet(x) && count < 50) {
                colors[x] = ktheNgjyre();

                count++;
                repaint();
                //Thread.sleep(5);

            }
            count = 0;
            if (!gjejKonfliktet(x))
                vargu.remove(rectangle[x]);
            ArrayList<Integer> kufiza = gjejKufizat(x);
            count = kufiza.size();
            for (int a : kufiza) {
                if (gjejKonfliktet(a) && vargu.contains(rectangle[a])) {
                    x = a;
                    count--;
                    continue Fillimi;
                }
            }
            if (count == kufiza.size()) {
                break;
            }
            repaint();
            //Thread.sleep(100);
        }
        //System.out.println("Ne varg kane mbetur edhe " + vargu.size());
    }

    public int numeroKonfliktet() {
        int numero = 0;
        for (int i = 0; i < rectangle.length; i++)
            if (gjejKonfliktet(i))
                numero++;

        return numero;
    }
    public int numeroKonfliktet(Color [] colors) {
        int numero = 0;
        for (int i = 0; i < rectangle.length; i++)
            if (gjejKonfliktet(i,colors))
                numero++;

        return numero;
    }


    public static void main(String[] args) {
        try {
            ColorStateGenetic c = new ColorStateGenetic();
            ArrayList<Rectangle> vargu = new ArrayList<>();
            c.jepVlere();
            for (int i = 0; i < c.rectangle.length; i++)
                vargu.add(c.rectangle[i]);

            for (int k = 0; k < 49; k++)
                c.hillClimbing(k * 2);

            c.hillClimbing(99);


            GjendjaFillestare G = new GjendjaFillestare(c.rectangle1, c.colors1);
            JFrame k2 = new JFrame();
            k2.setSize(500, 500);
            k2.getContentPane().add(G);
            k2.setTitle("Gjendja Fillestare");
            k2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            k2.setVisible(true);
            System.out.println("\n Konfliktet ne fillim : " + G.numeroKonfliktet());
            System.out.println("\n Konfliktet pas veprimit me algoritem :" + c.numeroKonfliktet());
            System.out.println();
            Color [] c3 = c.perfundo(30);
            GjendjaFillestare G2 = new GjendjaFillestare(c.rectangle1,c3);
            JFrame k3 = new JFrame();
            k3.setTitle("Color State Genetic");
            k3.getContentPane().add(G2);
            k3.setSize(500,500);
            k3.setVisible(true);
            k3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
             
        } catch (Exception e) {
        System.out.println(e);
        }

    }
}
