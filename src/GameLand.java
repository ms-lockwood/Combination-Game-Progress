
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable, KeyListener {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public boolean isPlaying=false;
    public boolean startScreen1=true;
    public boolean level1;
    public boolean startScreen2;
    public boolean level2;
    public boolean gameOver;

    public int score;

    //declare time variables
    public long startTime;
    public long currentTime;
    public long elapsedTime;

    //Declare the objects used in the program below
    /**
     * STEP 0: declare ARRAY LIST
     **/
    public ArrayList<Character> lemon;
    public ArrayList<Character> lime;
    public ArrayList<Character> strawberry;
    public ArrayList<Character> cherry;
    /**
     * STEP 1: declare image for object
     **/
    //public Image "astroPic;
    public Image lemonPic;
    public Image limePic;
    public Image strawberryPic;
    public Image cherryPic;
    public Image pink;
    public Image background2;
    public Image gameOverPic;
    //intersection booleans
    //public boolean astroIsIntersectingMoon=false;


    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method
        //create (construct) the objects needed for the game below
        /**STEP 2: construct object**/
        // coy=new Hero(200,300,3,-3,60,80);
        /**STEP 3: construct array list of characters**/

        /**STEP 3.5: add image to object**/
        // astroPic=Toolkit.getDefaultToolkit().getImage("astro.png");
        pink = Toolkit.getDefaultToolkit().getImage("pink.jpeg");
        background2 = Toolkit.getDefaultToolkit().getImage("blue.png");
        gameOverPic = Toolkit.getDefaultToolkit().getImage("gameOverPic.png");
        lemonPic = Toolkit.getDefaultToolkit().getImage("lemon.png");
        limePic = Toolkit.getDefaultToolkit().getImage("limereal.png");
        strawberryPic = Toolkit.getDefaultToolkit().getImage("strawberry.png");
        cherryPic = Toolkit.getDefaultToolkit().getImage("cherry.png");
        //for each object that has a picture, load in images as well


    }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.
    public void runCorrectLevel(){
        /**This method gets called when you click the space bar
         * It will DECIDE WHICH LEVEL METHODS TO CALL BASED ON BOOLEANS */
        if(startScreen1){
            startScreen1=false;
            level1=true;
            startLevel1();
            System.out.println("Level 1 begins now!");
        }
        if(level1&&score>14){//transition from level 1 to pause screen
            level1=false;
            startScreen2=true;
        }
        if (startScreen2){//transition from pause screen to level 2
            startScreen2=false;
            level2=true;
            startLevel2();
            // System.out.println("level 2 has started");
        }
        if(gameOver){ //restart the game transition from game over to level 1
            gameOver=false;
            level1=true;
            startLevel1();
        }
    }
    public void startLevel1(){
        //construct array of fruits
        lemon = new ArrayList<>();
        lime = new ArrayList<>();
        strawberry = new ArrayList<>();
        cherry = new ArrayList<>();
        for (int k = 0; k < 10; k = k + 1) { //we must use an int for k<10 and not lemon.size
            int randX = (int) (Math.random() * 1000);
            int randY = k*(-700);
            lemon.add(new Character(randX, randY, 0, 3, 100, 100));
        }
        for (int l = 0; l < 10; l = l + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = l*(-700);
            lime.add(new Character(randX, randY, 0, 3, 75, 75));
        }
        for (int s = 0; s < 10; s = s + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = s*(-700);
            strawberry.add(new Character(randX, randY, 0, 3, 50, 50));
        }
        for (int c = 0; c < 10; c = c + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = c*(-700);
            cherry.add(new Character(randX, randY, 0, 3, 50, 50));
        }
        //reset start time
        startTime=System.currentTimeMillis();
        //reset score
        score=0;
    }

    public void startLevel2(){
        lemon = new ArrayList<>();
        lime = new ArrayList<>();
        strawberry = new ArrayList<>();
        cherry = new ArrayList<>();
        for (int k = 0; k < 10; k = k + 1) { //we must use an int for k<10 and not lemon.size
            int randX = (int) (Math.random() * 1000);
            int randY = k*(-700);
            lemon.add(new Character(randX, randY, 0, 3, 100, 100));
        }
        for (int l = 0; l < 10; l = l + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = l*(-700);
            lime.add(new Character(randX, randY, 0, 3, 75, 75));
        }
        for (int s = 0; s < 10; s = s + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = s*(-700);
            strawberry.add(new Character(randX, randY, 0, 3, 50, 50));
        }
        for (int c = 0; c < 10; c = c + 1) {
            int randX = (int) (Math.random() * 1000);
            int randY = c*(-700);
            cherry.add(new Character(randX, randY, 0, 3, 50, 50));
        }
    }

    public void timer(){
        //get the current time
        currentTime=System.currentTimeMillis();
        //calculate the elapsed time, convert it to seconds and cast as an int
        elapsedTime=(int)((currentTime-startTime)*.001); //multiply to convert to sec
    }
    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            timer();
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //use if conditions around what images to render
        if(startScreen1){
            g.drawString("Press space bar to begin!", 400,300);
        }
        if(level1){
            g.drawImage(pink, 0, 0, WIDTH, HEIGHT, null);
            g.drawString("Score: "+score,100,60);
            g.drawString("Time: "+elapsedTime,900,60);
            if (!lemon.isEmpty()) {
                for (int k = 0; k < lemon.size(); k = k + 1) {
                    g.drawImage(lemonPic, lemon.get(k).xpos, lemon.get(k).ypos, lemon.get(k).width, lemon.get(k).height, null);
                }
            }
            if (!lime.isEmpty()) {
                for (int l = 0; l < lime.size(); l = l + 1) {
                    g.drawImage(limePic, lime.get(l).xpos, lime.get(l).ypos, lime.get(l).width, lime.get(l).height, null);
                }
            }
            if (!strawberry.isEmpty()) {
                for (int s = 0; s < strawberry.size(); s = s + 1) {
                    g.drawImage(strawberryPic, strawberry.get(s).xpos, strawberry.get(s).ypos, strawberry.get(s).width, strawberry.get(s).height, null);
                }
            }
            if (!cherry.isEmpty()) {
                for (int c = 0; c < cherry.size(); c = c + 1) {
                    g.drawImage(cherryPic, cherry.get(c).xpos, cherry.get(c).ypos, cherry.get(c).width, cherry.get(c).height, null);
                }
            }
        }
        if(startScreen2){
            g.drawString("Press space bar to begin Level 2", 400, 350);
        }
        if(level2){
            g.drawImage(background2,0,0,WIDTH,HEIGHT,null);
            g.drawString("Score: "+score,100,60);
            g.drawString("Time: "+elapsedTime,900,60);
        }
        if(gameOver){
            g.drawImage(gameOverPic,0,0,400,350,null);
        }

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();
        bufferStrategy.show();
    }

    public void moveThings() {
        // lime.move();
        //call the move() method code from your object class
        if (!cherry.isEmpty()) {
            for (int c = 0; c < cherry.size(); c = c + 1) {
                cherry.get(c).move();
            }
        }
        if (!strawberry.isEmpty()) {
            for (int s = 0; s < strawberry.size(); s = s + 1) {
                strawberry.get(s).move();
            }
        }
        if (!lime.isEmpty()) {
            for (int l = 0; l < lime.size(); l = l + 1) {
                lime.get(l).move();
            }
        }
        if (!lemon.isEmpty()) {
            for (int k = 0; k < lemon.size(); k = k + 1) {
                    lemon.get(k).move();
            }
        }
    }

    public void collisions() {
        /**STEP 6: use array list, check collisions, create new characters**/
        for (int c = 0; c < cherry.size(); c = c + 1) {
            for (int d = 0; d < cherry.size(); d = d + 1) {
                if (cherry.get(c).rec.intersects(cherry.get(d).rec) && cherry.get(c).isAlive && cherry.get(d).isAlive && c != d) {
                    strawberry.add(new Character(cherry.get(c).xpos, cherry.get(c).ypos, 0, 3, 30, 30));
                }
                cherry.get(c).isAlive = false;
                cherry.get(c).dx = 0;
                cherry.get(c).dy = 0;
            }
        }
        for (int s = 0; s < strawberry.size(); s = s + 1) {
            for (int m = 0; m < strawberry.size(); m = m + 1) {
                if (strawberry.get(s).rec.intersects(strawberry.get(m).rec) && strawberry.get(s).isAlive && strawberry.get(m).isAlive && s != m) {
                    lime.add(new Character(strawberry.get(s).xpos, strawberry.get(s).ypos, 0, 3, 30, 30));
                }
                strawberry.get(s).isAlive = false;
                strawberry.get(s).dx = 0;
                strawberry.get(s).dy = 0;
            }
        }
        for (int l = 0; l < lime.size(); l = l + 1) {
            for (int q = 0; q < lime.size(); q = q + 1) {
                if (lime.get(l).rec.intersects(lime.get(q).rec) && lime.get(l).isAlive && lime.get(q).isAlive && l != q) {
                    lemon.add(new Character(lime.get(l).xpos, lime.get(l).ypos, 0, 3, 30, 30));
                }
                lime.get(l).isAlive = false;
                lime.get(l).dx = 0;
                lime.get(l).dy = 0;
            }
        }
        if(level1&& score>14){
            level1=false;
            startScreen2=true;
        }
        if(level2&& score>30){
            level2=false;
            gameOver=true;
        }
    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //commonly ignored
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key: " + key + " Keycode: " + keyCode);
        for (int c = 0; c < cherry.size(); c = c + 1) {
            if (keyCode == 65) {//left
                cherry.get(c).leftPressed = true;
            }
            if (keyCode == 68) {//right
                cherry.get(c).rightPressed = true;
            }
        }
        for (int s = 0; s < strawberry.size(); s = s + 1) {
            if (keyCode == 65) {//left
                strawberry.get(s).leftPressed = true;
            }
            if (keyCode == 68) {//right
                strawberry.get(s).rightPressed = true;
            }
        }
        for (int l = 0; l < lime.size(); l = l + 1) {
            if (keyCode == 65) {//left
                lime.get(l).leftPressed = true;
            }
            if (keyCode == 68) {//right
                lime.get(l).rightPressed = true;
            }
        }
        for (int k = 0; k < lemon.size(); k = k + 1) {
            if (keyCode == 65) {//left
                lemon.get(k).leftPressed = true;
            }
            if (keyCode == 68) {//right
                lemon.get(k).rightPressed = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        for (int c = 0; c < cherry.size(); c = c + 1) {
            if (keyCode == 65) {//left
                cherry.get(c).leftPressed = false;
            }
            if (keyCode == 68) {//right
                cherry.get(c).rightPressed = false;
            }
        }
        for (int s = 0; s < strawberry.size(); s = s + 1) {
            if (keyCode == 65) {//left
                strawberry.get(s).leftPressed = false;
            }
            if (keyCode == 68) {//right
                strawberry.get(s).rightPressed = false;
            }
        }
        for (int l = 0; l < lime.size(); l = l + 1) {
            if (keyCode == 65) {//left
                lime.get(l).leftPressed = false;
            }
            if (keyCode == 68) {//right
                lime.get(l).rightPressed = false;
            }
        }
        for (int k = 0; k < lemon.size(); k = k + 1) {
            if (keyCode == 65) {//left
                lemon.get(k).leftPressed = false;
            }
            if (keyCode == 68) {//right
                lemon.get(k).rightPressed = false;
            }
        }
    }
}