package userInterface.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gamePresenter.BoardManager;
import models.Player;
import models.location.*;


public class Map extends JPanel{ 
	enum BoardSide{
		DOWN, LEFT, UP, RIGHT
	}
	
	Image mapImage;
	ArrayList<Location> locationsList;
	
	public Map() {
		mapImage = new ImageIcon("./resources/Board.jpg").getImage();
		setBounds(950, 25, 900, 900);
		//BoardManager.getInstance().updateMap();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		
		locationsList = new ArrayList<Location>();
		for(int i = 0; i < locationsList.size(); ++i) {
			Location toDraw = locationsList.get(i);
			
			switch(toDraw.getClass().toString()) {
			case "class models.location.StartTile":
				return;
			case "class models.location.Property":
				drawProperty(g, (Property) toDraw);
				return;
			case "class models.location.Disciplinary":
				drawDisciplinary(g, (Disciplinary) toDraw);
				return;
			case "class models.location.GoToDisciplinaryTile":
				drawGoToDisciplinaryTile(g, (GoToDisciplinaryTile) toDraw);
				return;
			case "class models.location.Utility":
				drawUtility(g, (Utility) toDraw);
				return;
			case "class models.location.BusStop":
				drawBusStop(g, (BusStop) toDraw);
				return;
			case "class models.location.MayfestTile":
				drawMayfestTile(g, (MayfestTile) toDraw);
				return;
			case "class models.location.ChanceTile":
				drawChanceTile(g, (ChanceTile) toDraw);
				return;
			case "class models.location.IncomeTaxTile":
				drawIncomeTaxTile(g, (IncomeTaxTile) toDraw);
				return;	
			default:
				System.out.println("An error occurred on Map.paint()");
			}
		}
		g.drawImage(mapImage, 0, 0, null); 
	}
	
	private void drawStartTile(Graphics g, StartTile toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawProperty(Graphics g, Property toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawChanceTile(Graphics g, ChanceTile toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawBusStop(Graphics g, BusStop toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawIncomeTaxTile(Graphics g, IncomeTaxTile toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawDisciplinary(Graphics g, Disciplinary toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawUtility(Graphics g, Utility toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawMayfestTile(Graphics g, MayfestTile toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	private void drawGoToDisciplinaryTile(Graphics g, GoToDisciplinaryTile toDraw) {
		drawPlayersHere(g, toDraw); 
	}
	
	public void update(ArrayList<Location> locationsList) {
		this.locationsList = locationsList;
		repaint();
	}
	
	public void drawPlayersHere(Graphics g, Location loc) {
		final int TOKEN_OFFSET = 20; 
		final int TOKEN_OFFSETFOR4PLAYERS = 20; 
		int secondOffset = 0;
		BoardSide sideOfLoc; 
		Point2D orgPoint = loc.getPoint();
		
		ArrayList<Player> playersHere = loc.getAllPlayersHere();
		
		int id = loc.getLocationId();
		if( id < 11 )
			sideOfLoc = BoardSide.DOWN;
		else if( id < 21)
			sideOfLoc = BoardSide.LEFT;
		else if( id < 31)
			sideOfLoc = BoardSide.UP;
		else
			sideOfLoc = BoardSide.RIGHT;
		
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
			//g.fillRect(xPoint, yPoint, 100, 100);  //for testing
			g.drawImage(playerToken, xPoint , yPoint, getWidth(), getHeight(), this);
		}
	}

}
