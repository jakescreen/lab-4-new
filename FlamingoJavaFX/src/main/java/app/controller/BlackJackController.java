package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.Flamingo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import netgame.common.Client;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;

public class BlackJackController implements Initializable {
	private Flamingo FlamingoGame;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void setMainApp(Flamingo FlamingoGame)
	{
		this.FlamingoGame = FlamingoGame;
		ArrayList<Player> players = new ArrayList<Player>();
		Table table = new Table();
	}
	@FXML
	public void btnSitLeave_Click(ActionEvent event)
	{
		Button btn = (Button) event.getSource();
		
		Action act = new Action();
		
	    act.setAction(btn.getText().equals("Sit") ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();
		
		if(btn.getId().equals("btnTOP")) {
			p.setiPlayerPosition(1);
			
		}
		else if(btn.getId().equals("btnBOTTOM")) {
			p.setiPlayerPosition(0);
			
		}	
		
		act.setPlayer(p);
		
		Action actionToSend = (Action) act;
		
		FlamingoGame.SendMessageToClient(actionToSend);
		
		System.out.println(btn.getId());
	}
	
	public void HandleTableState(Table t) {
		Button btnTOP = (Button) FlamingoGame.getStage().getScene().lookup("#btnTOP");
		Button btnBOTTOM = (Button) FlamingoGame.getStage().getScene().lookup("#btnBOTTOM");
		Label lblTOP = (Label) FlamingoGame.getStage().getScene().lookup("#lblTOP");
		Label lblBOTTOM = (Label) FlamingoGame.getStage().getScene().lookup("#lblBOTTOM");
		Boolean topPLAYERempty = true;
		Boolean bottomPLAYERempty = true;
		List<Player> playerList = t.GetTablePlayers();
		for(Player p : playerList) {
			if(p.getiPlayerPosition() == 1) {
				if(p.getiPokerClientID() == FlamingoGame.getAppPlayer().getiPokerClientID()) {
					btnTOP.setText("Leave");
					lblTOP.setText(p.getPlayerName());
					btnBOTTOM.setVisible(false);
					topPLAYERempty = false;
				}
				else {
					btnTOP.setVisible(false);
					lblTOP.setText(p.getPlayerName());
					topPLAYERempty = false;
				}
			}
			else if(p.getiPlayerPosition() == 0) {
				if(p.getiPokerClientID() == FlamingoGame.getAppPlayer().getiPokerClientID()) {
					btnBOTTOM.setText("Leave");
					lblBOTTOM.setText(p.getPlayerName());
					btnTOP.setVisible(false);
					bottomPLAYERempty = false;
				}
				else {
					btnBOTTOM.setVisible(false);
					lblBOTTOM.setText(p.getPlayerName());
					bottomPLAYERempty = false;
				}
			}
		}
		if(topPLAYERempty.equals(true) && bottomPLAYERempty.equals(true)) {
			btnBOTTOM.setText("Sit");
			lblBOTTOM.setText("");
			btnBOTTOM.setVisible(true);
			btnTOP.setText("Sit");
			lblTOP.setText("");
			btnTOP.setVisible(true);
		}
		else if(topPLAYERempty.equals(true)) {
			btnTOP.setText("Sit");
			lblTOP.setText("");
			
		}
		else if(bottomPLAYERempty.equals(true)) {
			btnBOTTOM.setText("Sit");
			lblBOTTOM.setText("");
			
		}
		
			
		
	}

	public void HandleGameState(GamePlay gp) {

		//		Coming Soon....!
	}

}
