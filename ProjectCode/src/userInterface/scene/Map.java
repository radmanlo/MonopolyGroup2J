package userInterface.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import gamePresenter.BoardManager;
import models.Player;
import models.PlayerColor;
import models.location.*;
import utilities.Utils;


public class Map extends JPanel{ 
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

	public Map() {
		mapImage = new ImageIcon("./resources/Board.jpg").getImage();
		setBounds(900, 10, 1000, 1000);
		locationsList = new ArrayList<Location>();
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
		
		//testing
		ImageToDraw vendingMachine = new ImageToDraw();
		vendingMachine.image = Utils.scaleImage(30,30,"./resources/Starbucks.jpg");
		vendingMachine.orgPoint = new Point2D.Double(147, 190);
		imagesToDraw.add(vendingMachine);	

		for( ImageToDraw imgO : imagesToDraw)
			g.drawImage(imgO.image,(int) imgO.orgPoint.getX(),(int) imgO.orgPoint.getY(), this);

		for( RectToDraw rectO : rectsToDraw) {
			Color color = getUsableColor(rectO.color);
			g.setColor(color);
			g.fillRect((int)rectO.orgPoint.getX(), (int)rectO.orgPoint.getY(), rectO.width, rectO.heigth);
		}
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
			Image playerToken = playersHere.get(i).getToken().getImage();

			if( i > 4)
				secondOffset = TOKEN_OFFSETFOR4PLAYERS;

			int xPoint = 0;
			int yPoint = 0;
			if( sideOfLoc == BoardSide.DOWN) {
				xPoint = (int) orgPoint.getX() - TOKEN_OFFSET * i;
				yPoint = (int) orgPoint.getY() + TOKEN_OFFSETFOR4PLAYERS;
			}else if(sideOfLoc == BoardSide.LEFT) {
				xPoint = (int) orgPoint.getX() - TOKEN_OFFSETFOR4PLAYERS;
				yPoint = (int) orgPoint.getY() - TOKEN_OFFSET * i;
			}else if(sideOfLoc == BoardSide.UP) {
				xPoint = (int) orgPoint.getX() + TOKEN_OFFSET * i;
				yPoint = (int) orgPoint.getY() - TOKEN_OFFSETFOR4PLAYERS;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				xPoint = (int) orgPoint.getX() + TOKEN_OFFSETFOR4PLAYERS;
				yPoint = (int) orgPoint.getY() + TOKEN_OFFSET * i;
			}
			ImageToDraw token = new ImageToDraw();
			token.image = playerToken;
			token.orgPoint = new Point2D.Double(xPoint, yPoint);
			imagesToDraw.add(token);
		}
	}
	
	private void drawOwnerOfHere(BuyableLocation loc) {
		final int OWNER_RECT_WIDTH = 20; 
		
		if( loc.getOwner() == null)
			return;
		
		RectToDraw rect = new RectToDraw();
		
		BoardSide sideOfLoc = getBoardSideById(loc.getLocationId()); 
		
		if( sideOfLoc == BoardSide.DOWN || sideOfLoc == BoardSide.UP) {
			rect.heigth = OWNER_RECT_WIDTH;
			rect.width = NORMAL_TILE_WIDTH;
		}else{
			rect.heigth = NORMAL_TILE_WIDTH;
			rect.width = OWNER_RECT_WIDTH;
		}
		
		rect.orgPoint = loc.getPoint();
		rect.color = loc.getOwner().getColorId();
		rectsToDraw.add(rect);
	}
	
	private void drawUpgradesOfHere(Property loc) {
		final int SPACE_BETWEEN_VENDINGMACHINES = 50;
		final int HEIGHT_FROM_ORG_POINT = 120;
		
		BoardSide sideOfLoc = getBoardSideById(loc.getLocationId()); 
		
		Point2D orgPoint = loc.getPoint();
		
		
		for( int i = 0; i < loc.getVendingMachinesNo(); ++i) {
			int xPoint = 0;
			int yPoint = 0;
			
			if( sideOfLoc == BoardSide.DOWN) {
				xPoint = (int) orgPoint.getX() - SPACE_BETWEEN_VENDINGMACHINES * i;
				yPoint = (int) orgPoint.getY() - HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.LEFT) {
				xPoint = (int) orgPoint.getX() + HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() - SPACE_BETWEEN_VENDINGMACHINES * i;
			}else if(sideOfLoc == BoardSide.UP) {
				xPoint = (int) orgPoint.getX() + SPACE_BETWEEN_VENDINGMACHINES * i;
				yPoint = (int) orgPoint.getY() + HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				xPoint = (int) orgPoint.getX() - HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() + SPACE_BETWEEN_VENDINGMACHINES * i;
			}
			
			ImageToDraw vendingMachine = new ImageToDraw();
			vendingMachine.image = new ImageIcon("./resources/VendingMachine.png").getImage();
			vendingMachine.orgPoint = new Point2D.Double(xPoint, yPoint);
			imagesToDraw.add(vendingMachine);	
		}
		
		if( loc.hasStarbucks()) {
			int xPoint = 0;
			int yPoint = 0;
			
			if( sideOfLoc == BoardSide.DOWN) {
				xPoint = (int) orgPoint.getX() - SPACE_BETWEEN_VENDINGMACHINES/2;
				yPoint = (int) orgPoint.getY() - HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.LEFT) {
				xPoint = (int) orgPoint.getX() + HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() - SPACE_BETWEEN_VENDINGMACHINES/2;
			}else if(sideOfLoc == BoardSide.UP) {
				xPoint = (int) orgPoint.getX() + SPACE_BETWEEN_VENDINGMACHINES/2;
				yPoint = (int) orgPoint.getY() + HEIGHT_FROM_ORG_POINT;
			}else if(sideOfLoc == BoardSide.RIGHT) {
				xPoint = (int) orgPoint.getX() - HEIGHT_FROM_ORG_POINT;
				yPoint = (int) orgPoint.getY() + SPACE_BETWEEN_VENDINGMACHINES/2;
			}
			
		
			ImageToDraw starbucks = new ImageToDraw();
			starbucks.image = new ImageIcon("./resources/Starbucks.jpg").getImage();
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

}
