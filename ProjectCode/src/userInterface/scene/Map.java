package userInterface.scene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import models.Player;
import models.PlayerColor;
import models.location.*;
import utilities.Utils;


public class Map extends JPanel {
	enum BoardSide{
		DOWN, LEFT, UP, RIGHT
	}
	class ImageToDraw
	{
		public Image image; 
		public Point2D orgPoint; 
	};
	class RectToDraw
	{
		public Point2D orgPoint;
		public int width;
		public int heigth;
		public PlayerColor color;
	}


	Image mapImage;
	ArrayList<Location> locationsList;
	ArrayList<ImageToDraw> imagesToDraw;
	ArrayList<RectToDraw> rectsToDraw;
	final int NORMAL_TILE_WIDTH = 73;
	final int LARGE_TILE_WIDTH = 120;

	private DiceAnimationPanel diceAnimation;

	public Map() {
		mapImage = new ImageIcon("./resources/Board2.jpg").getImage();
		setBounds(900, 10, 1000, 1000);
		locationsList = new ArrayList<Location>();
		setLayout(null);
		addDiceAnimation();
	}

	private void addDiceAnimation() {
		diceAnimation = new DiceAnimationPanel();
		diceAnimation.setBounds(280, 200, 500, 210);
		add(diceAnimation);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.drawImage(mapImage, 0, 0, null);

		imagesToDraw = new ArrayList<ImageToDraw>();
		rectsToDraw = new ArrayList<RectToDraw>();

		for(int i = 0; i < locationsList.size(); ++i) {
			Location toDraw = locationsList.get(i);
			switch(toDraw.getClass().toString()) {
			case "class models.location.StartTile":
				drawStartTile((StartTile) toDraw);
				break;
			case "class models.location.Property":
				drawProperty((Property) toDraw);
				break;
			case "class models.location.Disciplinary":
				drawDisciplinary((Disciplinary) toDraw);
				break;
			case "class models.location.GoToDisciplinaryTile":
				drawGoToDisciplinaryTile((GoToDisciplinaryTile) toDraw);
				break;
			case "class models.location.Utility":
				drawUtility((Utility) toDraw);
				break;
			case "class models.location.BusStop":
				drawBusStop((BusStop) toDraw);
				break;
			case "class models.location.MayfestTile":
				drawMayfestTile((MayfestTile) toDraw);
				break;
			case "class models.location.ChanceTile":
				drawChanceTile((ChanceTile) toDraw);
				break;
			case "class models.location.IncomeTaxTile":
				drawIncomeTaxTile((IncomeTaxTile) toDraw);
				break;	
			default:
				System.out.println("An error occurred on Map.paint()");
			}
		}

		for( RectToDraw rectO : rectsToDraw) {
			Color color = getUsableColor(rectO.color);
			g.setColor(color);
			g.fillRect((int)rectO.orgPoint.getX(), (int)rectO.orgPoint.getY(), rectO.width, rectO.heigth);
		}
		for( ImageToDraw imgO : imagesToDraw)
			g.drawImage(imgO.image,(int) imgO.orgPoint.getX(),(int) imgO.orgPoint.getY(), this);
	}

	private Color getUsableColor(PlayerColor playerColor) {
		Color PURPLE = new Color(102,0,153);
		switch(playerColor) {
		case BLACK:
			return java.awt.Color.BLACK;
		case BLUE:
			return java.awt.Color.BLUE;
		case GREEN:
			return java.awt.Color.GREEN;
		case ORANGE:
			return java.awt.Color.ORANGE;
		case PURPLE:
			return PURPLE;
		case RED:
			return java.awt.Color.RED;
		case WHITE:
			return java.awt.Color.WHITE;
		case YELLOW:
			return java.awt.Color.YELLOW;
		default:
			return null;
		}
	}

	public void update(ArrayList<Location> locationsList) {
		this.locationsList = locationsList;
		repaint();
	}

	private void drawStartTile(StartTile toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawProperty(Property toDraw) {
		drawPlayersHere(toDraw);
		drawOwnerOfHere(toDraw);
		drawUpgradesOfHere(toDraw);
	}

	private void drawChanceTile(ChanceTile toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawBusStop(BusStop toDraw) {
		drawPlayersHere(toDraw); 
		drawOwnerOfHere(toDraw);
	}

	private void drawIncomeTaxTile(IncomeTaxTile toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawDisciplinary(Disciplinary toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawUtility(Utility toDraw) {
		drawPlayersHere(toDraw); 
		drawOwnerOfHere(toDraw);
	}

	private void drawMayfestTile(MayfestTile toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawGoToDisciplinaryTile(GoToDisciplinaryTile toDraw) {
		drawPlayersHere(toDraw); 
	}

	private void drawPlayersHere(Location loc) {
		final int TOKEN_OFFSET = 20; 
		final int TOKEN_OFFSETFOR4PLAYERS = 20; 
		int secondOffset = 0;
		Point2D orgPoint = loc.getPoint();

		ArrayList<Player> playersHere = loc.getAllPlayersHere();

		BoardSide sideOfLoc = getBoardSideById(loc.getLocationId()); 
		
		for(int i=0; i< playersHere.size(); ++i) {
			String playerTokenPath = playersHere.get(i).getToken().getPath();

			if( i > 4)
				secondOffset = TOKEN_OFFSETFOR4PLAYERS;

			int xPoint = 0;
			int yPoint = 0;
			if( sideOfLoc == BoardSide.DOWN) {
				xPoint = (int) orgPoint.getX() - 20 - TOKEN_OFFSET * i;
				yPoint = (int) orgPoint.getY() - 20 + TOKEN_OFFSETFOR4PLAYERS;
			}else if(sideOfLoc == BoardSide.LEFT) {
				xPoint = (int) orgPoint.getX() - TOKEN_OFFSETFOR4PLAYERS;
				yPoint = (int) orgPoint.getY() - 20 -TOKEN_OFFSET * i;
			}else if(sideOfLoc == BoardSide.UP) {
				xPoint = (int) orgPoint.getX() + TOKEN_OFFSET * i;
				yPoint = (int) orgPoint.getY() - TOKEN_OFFSETFOR4PLAYERS;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				xPoint = (int) orgPoint.getX() -20 + TOKEN_OFFSETFOR4PLAYERS;
				yPoint = (int) orgPoint.getY() + TOKEN_OFFSET * i;
			}
			ImageToDraw token = new ImageToDraw();
			token.image = Utils.scaleImage(20,20,playerTokenPath);
			token.orgPoint = new Point2D.Double(xPoint, yPoint);
			imagesToDraw.add(token);
		}
	}
	
	private void drawOwnerOfHere(BuyableLocation loc) {
		final int OWNER_RECT_WIDTH = 20; 
		
		Point2D orgPoint = loc.getPoint();
		int xPoint = (int) orgPoint.getX();
		int yPoint = (int) orgPoint.getY();
		
		if( loc.getOwner() == null)
			return;
		
		RectToDraw rect = new RectToDraw();
		
		BoardSide sideOfLoc = getBoardSideById(loc.getLocationId()); 
		
		
		if( sideOfLoc == BoardSide.DOWN) {
			rect.heigth = OWNER_RECT_WIDTH;
			rect.width = NORMAL_TILE_WIDTH;
			xPoint -= NORMAL_TILE_WIDTH;
		}else if(sideOfLoc == BoardSide.LEFT) {
			rect.heigth = NORMAL_TILE_WIDTH;
			rect.width = OWNER_RECT_WIDTH;
			xPoint -= OWNER_RECT_WIDTH;
			yPoint -= NORMAL_TILE_WIDTH;
		}else if(sideOfLoc == BoardSide.UP) {
			rect.heigth = OWNER_RECT_WIDTH;
			rect.width = NORMAL_TILE_WIDTH;
			yPoint -= OWNER_RECT_WIDTH;
		}else if(sideOfLoc == BoardSide.RIGHT) {
			rect.heigth = NORMAL_TILE_WIDTH;
			rect.width = OWNER_RECT_WIDTH;
		}
		
		rect.orgPoint = new Point2D.Double(xPoint,yPoint);
		rect.color = loc.getOwner().getColorId();
		rectsToDraw.add(rect);
	}
	
	private void drawUpgradesOfHere(Property loc) {
		final int SPACE_BETWEEN_VENDINGMACHINES = 35;
		final int HEIGHT_FROM_ORG_POINT = 110;
		final int STARBUCKS_HEIGHT_OFFSET = 150;
		final int EXTRA_HEIGHT_OFFSET = 145;
		final int STARBUCKS_WIDTH_OFFSET = 55;
		BoardSide sideOfLoc = getBoardSideById(loc.getLocationId()); 
		
		Point2D orgPoint = loc.getPoint();
		if( !loc.hasStarbucks()) {
			
		for( int i = 0; i < loc.getVendingMachinesNo(); ++i) {
			int xPoint = 0;
			int yPoint = 0;
			ImageToDraw vendingMachine = new ImageToDraw();
			
			if( sideOfLoc == BoardSide.DOWN) {
				vendingMachine.image = Utils.scaleImage(25,30,"./resources/VendingMachine_Down.png");
				xPoint = (int) orgPoint.getX() - SPACE_BETWEEN_VENDINGMACHINES -SPACE_BETWEEN_VENDINGMACHINES * i;
				yPoint = (int) orgPoint.getY() - EXTRA_HEIGHT_OFFSET;
			}else if(sideOfLoc == BoardSide.LEFT) {
				vendingMachine.image = Utils.scaleImage(30,25,"./resources/VendingMachine_Left.png");
				xPoint = (int) orgPoint.getX() + HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() - SPACE_BETWEEN_VENDINGMACHINES - SPACE_BETWEEN_VENDINGMACHINES * i;
			}else if(sideOfLoc == BoardSide.UP) {
				vendingMachine.image = Utils.scaleImage(25,30,"./resources/VendingMachine_Up.png");
				xPoint = (int) orgPoint.getX() + STARBUCKS_WIDTH_OFFSET - 50 + SPACE_BETWEEN_VENDINGMACHINES * i;
				yPoint = (int) orgPoint.getY() + HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				vendingMachine.image = Utils.scaleImage(30,25,"./resources/VendingMachine_Right.png");
				xPoint = (int) orgPoint.getX() + 15 - STARBUCKS_HEIGHT_OFFSET;
				yPoint = (int) orgPoint.getY() + 5 + SPACE_BETWEEN_VENDINGMACHINES * i;
			}
			
			vendingMachine.orgPoint = new Point2D.Double(xPoint, yPoint);
			imagesToDraw.add(vendingMachine);	
		}
	}
		if( loc.hasStarbucks()) {
			ImageToDraw starbucks = new ImageToDraw();
			
			int xPoint = 0;
			int yPoint = 0;
			
			if( sideOfLoc == BoardSide.DOWN) {
				starbucks.image = Utils.scaleImage(40,40,"./resources/Starbucks_Down.png");
				xPoint = (int) orgPoint.getX() - STARBUCKS_WIDTH_OFFSET;
				yPoint = (int) orgPoint.getY() - STARBUCKS_HEIGHT_OFFSET;
			}else if(sideOfLoc == BoardSide.LEFT) {
				starbucks.image = Utils.scaleImage(40,40,"./resources/Starbucks_Left.png");
				xPoint = (int) orgPoint.getX() + HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() - STARBUCKS_WIDTH_OFFSET;
			}else if(sideOfLoc == BoardSide.UP) {
				starbucks.image = Utils.scaleImage(40,40,"./resources/Starbucks_Up.png");
				xPoint = (int) orgPoint.getX() + STARBUCKS_WIDTH_OFFSET - 40;
				yPoint = (int) orgPoint.getY() + HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				starbucks.image = Utils.scaleImage(40,40,"./resources/Starbucks_Right.png");
				xPoint = (int) orgPoint.getX() - STARBUCKS_HEIGHT_OFFSET;
				yPoint = (int) orgPoint.getY() + SPACE_BETWEEN_VENDINGMACHINES/2;
			}
			
			starbucks.orgPoint = new Point2D.Double(xPoint, yPoint);
			imagesToDraw.add(starbucks);
		}	
	}
	
	private BoardSide getBoardSideById(int id) {
		if( id < 11 )
			return BoardSide.DOWN;
		else if( id < 21)
			return BoardSide.LEFT;
		else if( id < 31)
			return BoardSide.UP;
		else
			return BoardSide.RIGHT;
	}

	/**
	 * Animates the dice
	 */
	public void animateDies(int firsDiceResult, int secondDiceResult) {
		diceAnimation.rollDies(firsDiceResult, secondDiceResult);
	}

}

class DiceAnimationPanel extends JPanel {
	private ArrayList<Icon> icons;
	private JLabel dice1;
	private JLabel dice2;
	private Timer timer;
	private Timer timer2;

	public DiceAnimationPanel() {
		dice1 = new JLabel();
		dice2 = new JLabel();
		// Center items inside the panel
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(Color.white);
		setLayout(null);

		// Don't show dies at the beginning
		setVisible(false);

		loadIcons();
	}

	/**
	 * Sets the icons of labels to the given icons
	 * @param dice1 first dice icon
	 * @param dice2 second dice icon
	 */
	private void setLabels(Icon dice1, Icon dice2) {
		// Set Label icons
		this.dice1.setIcon(dice1);
		this.dice2.setIcon(dice2);

		repaint();
	}


	/**
	 * Loads all 6 dice faces as Icon objects. Later to be changed based on the dice value
	 *
	 */
	public void loadIcons() {
		this.icons = new ArrayList<>();
		for(int i = 1; i < 7; ++i) {
			String path = "resources/dies/dice" + i + ".png";
			Icon icon = new ImageIcon(Utils.scaleImage(200, 200, path));
			icons.add(icon);
		}
	}

	/**
	 * Animates the dies. Uses a timer to dynamically change dice images for some time
	 */
	public void rollDies(int firsDiceResult, int secondDiceResult) {

		if(timer != null && timer.isRunning()) {
			timer.stop();
		}

		setVisible(true);
		addDies();

		timer = new Timer(300, new ActionListener() {
			// For counting the delay and stoping timer
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// Generate die values
				Random random = new Random();
				int rand1 = random.nextInt(5 - 1 + 1) + 1;
				int rand2 = random.nextInt(5 - 1 + 1) + 1;
				setLabels(icons.get(rand1), icons.get(rand2));

				count++;
				// Stop timer after the delay
				if(count >= 5) {
					setLabels(icons.get(firsDiceResult - 1), icons.get(secondDiceResult - 1));
					waitAndRemove();
					timer.stop();
				}
			}
		});
		timer.start();
		timer.setRepeats(true);
	}

	/**
	 * Waits for a couple of seconds after the dies are rolled and remove them from the panel
	 */
	private void waitAndRemove() {
		if(timer2 != null && timer2.isRunning()) {
			timer2.stop();
		}
		timer2 = new Timer(300, new ActionListener() {
			// For counting the delay and stoping timer
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				// Stop timer after the delay
				if(count >= 10) {
					removeAll();
					revalidate();
					repaint();
					setVisible(false);
					timer2.stop();
				}
			}
		});

		timer2.restart();
	}

	/**
	 * sets dies bounds and adds them to the panel
	 */
	private void addDies() {
		dice1.setBounds(10, 0, 200, 200);
		dice2.setBounds(240, 0, 200, 200);
		add(this.dice1);
		add(this.dice2);
	}


}
