import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class JavaFXTemplate extends Application {

	Player playerOne;
	Player playerTwo;
	Dealer theDealer;

	private EventHandler<ActionEvent> myHandler, myHandler2;
	Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
	TextField t1,t2,t3,t4,t5,t6;
	MenuItem mOne,mTwo,mThree;
	Button b1,b2,b3,b4,b5,b6;
	Boolean betPlacedp1, betPlacedp2;
	Boolean wagerPlacedp1,wagerPlacedp2;
	Boolean foldp1,foldp2;
	int winningsp1=0;
	int winningsp2=0;
	int sceneCount = 1;



	HBox hbox, hboxDealer, hbox5;
	BorderPane borderPane;
	VBox vbox3;

	HashMap<String, Scene> sceneMap;

	PauseTransition pause = new PauseTransition(Duration.seconds(1));
	PauseTransition pause2 = new PauseTransition(Duration.seconds(5));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("3 Card Poker");

		myHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//System.out.println("button pressed: " + ((Button)e.getSource()).getText());
				Button b = (Button) e.getSource();
				int anteBetp1 = 0;
				int anteBetp2 = 0;
				int ppBetp1 = 0;
				int ppBetp2 = 0;
				if(!t1.getText().equals("")) {
					anteBetp1 = Integer.parseInt(t1.getText());
				}
				if(!t2.getText().equals("")) {
					ppBetp1 = Integer.parseInt(t2.getText());
				}
				if(!t4.getText().equals("")) {
					anteBetp2 = Integer.parseInt(t4.getText());
				}
				if(!t5.getText().equals("")) {
					ppBetp2 = Integer.parseInt(t5.getText());
				}
				if((anteBetp1 >= 5 && anteBetp1 <= 25 && ppBetp1 <= 25) && (ppBetp1 >= 5 || ppBetp1 == 0)){
					betPlacedp1 = true;
					if(b == b1) {
						b.setDisable(true);
					}

				}
				if((anteBetp2 >= 5 && anteBetp2 <= 25 && ppBetp2 <= 25) && (ppBetp2 >= 5 || ppBetp2 == 0)) {
					betPlacedp2 = true;
					if(b == b4) {
						b.setDisable(true);
					}
				}
				if(betPlacedp1 && betPlacedp2 && b1.isDisable() && b4.isDisable()) {
					playerOne.setAnteBet(anteBetp1);
					playerTwo.setAnteBet(anteBetp2);
					playerOne.setPairPlusBet(ppBetp1);
					playerTwo.setPairPlusBet(ppBetp2);
					b2.setDisable(false);
					b3.setDisable(false);
					b5.setDisable(false);
					b6.setDisable(false);
					t1.setDisable(true);
					t2.setDisable(true);
					t3.setDisable(false);
					t4.setDisable(true);
					t5.setDisable(true);
					t6.setDisable(false);
					playerOne.setHand(theDealer.dealHand());
					playerTwo.setHand(theDealer.dealHand());
					pause.setOnFinished(ev->dealCards());
					pause.play();
					l11.setText("   Place Wagers or Fold\n" +
							"Wagers must be same as Ante Bet");
				}
			}
		};

		myHandler2 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button b = (Button) e.getSource();
				int playWagerp1 = 0;
				int playWagerp2 = 0;
				if(b == b3) {
					foldp1 = true;
					winningsp1 -= playerOne.getAnteBet();
					b2.setDisable(true);
					b3.setDisable(true);
					t3.setDisable(true);
				}
				if(b == b6) {
					foldp2 = true;
					winningsp2 -= playerTwo.getAnteBet();
					b5.setDisable(true);
					b6.setDisable(true);
					t6.setDisable(true);
				}
				if(!t3.getText().equals("")) {
					playWagerp1 = Integer.parseInt(t3.getText());
				}
				if(!t6.getText().equals("")) {
					playWagerp2 = Integer.parseInt(t6.getText());
				}
				if(b==b2) {
					if(playWagerp1 == playerOne.getAnteBet()) {
						wagerPlacedp1 = true;
						b2.setDisable(true);
						b3.setDisable(true);
						t3.setDisable(true);
					}
				}
				if(b == b5) {
					if(playWagerp2 == playerTwo.getAnteBet()) {
						wagerPlacedp2 = true;
						b5.setDisable(true);
						b6.setDisable(true);
						t6.setDisable(true);
					}
				}
				if(wagerPlacedp1 && foldp2) {
					playerOne.setPlayBet(playWagerp1);
					theDealer.setDealersHand(theDealer.dealHand());
					ArrayList<Card> cards = new ArrayList<>();
					cards.add(new Card('C',8));
					cards.add(new Card('C', 7));
					cards.add(new Card('C', 6));
					theDealer.setDealersHand(cards);
					DealerCards();
					int ppWinnings = ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet());
					if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 0) {
						while(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 0) {
							theDealer.setDealersHand(theDealer.dealHand());
							DealerCards();
						}
					}
					if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 1){
						pause.setOnFinished(ev->{l11.setText("Player One Loses to Dealer!\n" +
								"P1 Pair Plus Winnings: $" + ppWinnings + "\n" +
								"Player Two Folds");});
						pause.play();
						winningsp1 -= playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings;
						restart(primaryStage);
					}
					else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 2) {
						pause.setOnFinished(ev->{l11.setText("Player One Beats Dealer!\n" +
								"P1 Pair Plus Winnings: $" + ppWinnings + "\n" +
								"Player Two Folds");});
						pause.play();
						winningsp1 += playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings;
						sceneMap.get("scene").setOnMouseClicked(event->{primaryStage.setScene(sceneMap.get("scene"));});
						restart(primaryStage);
					}
				}
				if(wagerPlacedp2 && foldp1) {
					playerTwo.setPlayBet(playWagerp2);
					theDealer.setDealersHand(theDealer.dealHand());
					DealerCards();
					int ppWinnings = ThreeCardLogic.evalPPWinnings(playerTwo.getHand(), playerTwo.getPairPlusBet());
					if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 0) {
						while(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 0) {
							theDealer.setDealersHand(theDealer.dealHand());
							DealerCards();
						}
					}
					if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 1){
						pause.setOnFinished(ev->{l11.setText("Player One Folds\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings + "\n" +
								"Player Two Loses to Dealer!");});
						pause.play();
						winningsp2 -= playerTwo.getAnteBet()*2;
						winningsp2 += ppWinnings;
						restart(primaryStage);
					}
					else if(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 2) {
						pause.setOnFinished(ev->{l11.setText("Player One Folds\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings + "\n" +
								"Player Two Beats Dealer!");});
						pause.play();
						winningsp2 += playerOne.getAnteBet()*2;
						winningsp2 += ppWinnings;
						restart(primaryStage);
					}
				}
				if(wagerPlacedp1 && wagerPlacedp2) {
					playerOne.setPlayBet(playWagerp1);
					playerTwo.setPlayBet(playWagerp2);
					theDealer.setDealersHand(theDealer.dealHand());
					DealerCards();
					int ppWinnings1 = ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet());
					int ppWinnings2 = ThreeCardLogic.evalPPWinnings(playerTwo.getHand(), playerTwo.getPairPlusBet());
					if((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 0) ||
							(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 0)){
						while((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 0) ||
								(ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 0)) {
							theDealer.setDealersHand(theDealer.dealHand());
							DealerCards();
						}
					}
					if((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 1) &&
							((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 1))){
						pause.setOnFinished(ev->{l11.setText("Player One Loses to Dealer!\n" +
										"P1 Pair Plus Winnings: $" + ppWinnings1 + "\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings2 + "\n" +
								"Player Two Loses to Dealer!");});
						pause.play();
						winningsp1 -= playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings1;
						winningsp2 -= playerTwo.getAnteBet()*2;
						winningsp2 += ppWinnings2;
						restart(primaryStage);
					}
					else if((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 2) &&
							((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 1))){
						pause.setOnFinished(ev->{l11.setText("Player One Beats Dealer!\n" +
								"P1 Pair Plus Winnings: $" + ppWinnings1 + "\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings2 + "\n" +
								"Player Two Loses to Dealer!");});
						pause.play();
						winningsp1 += playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings1;
						winningsp2 -= playerTwo.getAnteBet()*2;
						winningsp2 += ppWinnings2;
						restart(primaryStage);
					}
					else if((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 1) &&
							((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 2))){
						pause.setOnFinished(ev->{l11.setText("Player One Loses to Dealer!\n" +
								"P1 Pair Plus Winnings: $" + ppWinnings1 + "\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings2 + "\n" +
								"Player Two Beats Dealer!");});
						pause.play();
						winningsp1 -= playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings1;
						winningsp2 += playerTwo.getAnteBet()*2;
						winningsp2 += ppWinnings2;
						restart(primaryStage);
					}
					else if((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand()) == 2) &&
							((ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand()) == 2))){
						pause.setOnFinished(ev->{l11.setText("Player One Beats Dealer!\n" +
								"P1 Pair Plus Winnings: $" + ppWinnings1 + "\n" +
								"P2 Pair Plus Winnings: $" + ppWinnings2 + "\n" +
								"Player Two Beats Dealer!");});
						pause.play();
						winningsp1 += playerOne.getAnteBet()*2;
						winningsp1 += ppWinnings1;
						winningsp2 += playerTwo.getAnteBet()*2;
						winningsp2 += ppWinnings2;
						restart(primaryStage);
					}
				}
				if(foldp1 && foldp2){
					theDealer.setDealersHand(theDealer.dealHand());
					DealerCards();
					pause.setOnFinished(ev->{l11.setText("Player One Folds\n" +
							"Player Two Folds");});
					pause.play();
					restart(primaryStage);
				}

			}
		};

		sceneMap = new HashMap<String,Scene>();
		sceneMap.put("scene", originalScene(primaryStage));

		mTwo.setOnAction(e->{winningsp1=0;winningsp2=0;sceneCount++;
			sceneMap.put("scene"+sceneCount, originalScene(primaryStage));
			primaryStage.setScene(sceneMap.get("scene"+sceneCount));});
		mThree.setOnAction(e->{sceneMap.put("newLook", newLookScene(primaryStage));
			primaryStage.setScene(sceneMap.get("newLook"));});

		primaryStage.setScene(sceneMap.get("scene"));
		primaryStage.show();

	}

	public Scene originalScene(Stage primaryStage) {
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
		betPlacedp1 = false;
		betPlacedp2 = false;
		foldp1 = false;
		foldp2 = false;
		wagerPlacedp1 = false;
		wagerPlacedp2 = false;
		hbox = new HBox();
		l1 = new Label("Ante Bet");
		l1.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t1 = new TextField();
		l2 = new Label("Pair Plus Bet");
		l2.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t2 = new TextField();
		l3 = new Label("Play Wager");
		l3.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t3 = new TextField();
		t3.setDisable(true);
		l4 = new Label("Player One");
		l4.setStyle("-fx-border-color: Black; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		b1 = new Button("Place Bet");
		b1.setStyle("-fx-background-color: Red; -fx-font-size: 2em;");
		b1.setOnAction(myHandler);
		b2 = new Button("Play");
		b2.setStyle("-fx-background-color: Magenta; -fx-font-size: 2em;");
		b2.setOnAction(myHandler2);
		b2.setDisable(true);
		b3 = new Button("Fold");
		b3.setStyle("-fx-background-color: Magenta; -fx-font-size: 2em;");
		b3.setOnAction(myHandler2);
		b3.setDisable(true);

		l5 = new Label("Ante Bet");
		l5.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t4 = new TextField();
		l6 = new Label("Pair Plus Bet");
		l6.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t5 = new TextField();
		l7 = new Label("Play Wager");
		l7.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t6 = new TextField();
		t6.setDisable(true);
		l8 = new Label("Player Two");
		l8.setStyle("-fx-border-color: Black; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		b4 = new Button("Place Bet");
		b4.setStyle("-fx-background-color: Red; -fx-font-size: 2em;");
		b4.setOnAction(myHandler);
		b5 = new Button("Play");
		b5.setStyle("-fx-background-color: Magenta; -fx-font-size: 2em;");
		b5.setOnAction(myHandler2);
		b5.setDisable(true);
		b6 = new Button("Fold");
		b6.setStyle("-fx-background-color: Magenta; -fx-font-size: 2em;");
		b6.setOnAction(myHandler2);
		b6.setDisable(true);

		l9 = new Label("Total Winnings: $" + winningsp1);
		l9.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		l10 = new Label("Total Winnings: $" + winningsp2);
		l10.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		HBox hbox1 = new HBox(b1,b2,b3);
		VBox vbox1 = new VBox(l1,t1,l2,t2,l3,t3,l4,hbox1);
		HBox hbox2 = new HBox(b4,b5,b6);
		VBox vbox2 = new VBox(l5,t4,l6,t5,l7,t6,l8,hbox2);
		HBox hbox3 = new HBox(l9,l10);
		hbox3.setSpacing(740);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Options");
		mOne = new MenuItem("Exit");
		mOne.setOnAction(e->{Platform.exit();});
		mTwo = new MenuItem("Fresh Start");
		mTwo.setOnAction(e->{winningsp1=0;winningsp2=0;sceneCount++;
			sceneMap.put("scene"+sceneCount, originalScene(primaryStage));
			primaryStage.setScene(sceneMap.get("scene"+sceneCount));});
		mThree = new MenuItem("New Look");
		mThree.setOnAction(e->{sceneMap.put("newLook", newLookScene(primaryStage));
			primaryStage.setScene(sceneMap.get("newLook"));});
		menu.getItems().addAll(mOne,mTwo, mThree);
		menuBar.getMenus().add(menu);
		menu.setStyle("-fx-border-width: 2px; -fx-font-size: 2em; -fx; -fx-border-color: black; -fx-background-color: gray");

		l11 = new Label("         Game Start!\n" +
				"   Place Ante Bet: 5-25\n" +
				"  Optional PP Bet: 5-25");
		l11.setStyle("-fx-border-color: Black; -fx-border-width: 4px; -fx-font-size: 3em; -fx-text-fill: White");
		HBox hbox4 = new HBox(menuBar,l11);
		hbox4.setSpacing(270);
		borderPane = new BorderPane();
		borderPane.setPrefSize(1200,700);
		Image image = new Image(getClass().getResourceAsStream("PokerBackground.jpg"));
		Image cardBack = new Image("CardBack.png");
		ImageView cardBackView = new ImageView(cardBack);
		cardBackView.setFitHeight(200);
		cardBackView.setFitWidth(150);
		BackgroundSize bSize = new BackgroundSize(700, 700, true, true, true, true);
		borderPane.setBackground(new Background(new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		borderPane.setLeft(vbox1);
		borderPane.setRight(vbox2);
		borderPane.setBottom(hbox3);
		borderPane.setTop(hbox4);

		Label l13 = new Label("     ");
		hboxDealer = new HBox();
		hboxDealer.setSpacing(5);
		Label l14 = new Label("     ");
		hbox5 = new HBox(l13,cardBackView, l14,hboxDealer);
		vbox3 = new VBox(hbox5,hbox);
		hbox.setSpacing(5);
		borderPane.setCenter(vbox3);

		l9.setAlignment(Pos.BOTTOM_LEFT);
		l10.setAlignment(Pos.BOTTOM_RIGHT);
		vbox1.setAlignment(Pos.CENTER_LEFT);
		vbox2.setAlignment(Pos.CENTER_RIGHT);

		return new Scene(new VBox(borderPane), 1200,700);
	}

	public Scene newLookScene(Stage primaryStage) {
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
		betPlacedp1 = false;
		betPlacedp2 = false;
		foldp1 = false;
		foldp2 = false;
		wagerPlacedp1 = false;
		wagerPlacedp2 = false;
		hbox = new HBox();
		l1 = new Label("Ante Bet");
		l1.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t1 = new TextField();
		l2 = new Label("Pair Plus Bet");
		l2.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t2 = new TextField();
		l3 = new Label("Play Wager");
		l3.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t3 = new TextField();
		t3.setDisable(true);
		l4 = new Label("Player One");
		l4.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		b1 = new Button("Place Bet");
		b1.setStyle("-fx-background-color: Blue; -fx-font-size: 2em;");
		b1.setOnAction(myHandler);
		b2 = new Button("Play");
		b2.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em;");
		b2.setOnAction(myHandler2);
		b2.setDisable(true);
		b3 = new Button("Fold");
		b3.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em;");
		b3.setOnAction(myHandler2);
		b3.setDisable(true);

		l5 = new Label("Ante Bet");
		l5.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t4 = new TextField();
		l6 = new Label("Pair Plus Bet");
		l6.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t5 = new TextField();
		l7 = new Label("Play Wager");
		l7.setStyle("-fx-border-color: Red; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Red");
		t6 = new TextField();
		t6.setDisable(true);
		l8 = new Label("Player Two");
		l8.setStyle("-fx-border-color: Green; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		b4 = new Button("Place Bet");
		b4.setStyle("-fx-background-color: Blue; -fx-font-size: 2em;");
		b4.setOnAction(myHandler);
		b5 = new Button("Play");
		b5.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em;");
		b5.setOnAction(myHandler2);
		b5.setDisable(true);
		b6 = new Button("Fold");
		b6.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em;");
		b6.setOnAction(myHandler2);
		b6.setDisable(true);

		l9 = new Label("Total Winnings: $" + winningsp1);
		l9.setStyle("-fx-border-color: Brown; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		l10 = new Label("Total Winnings: $" + winningsp2);
		l10.setStyle("-fx-border-color: Brown; -fx-border-width: 4px; -fx-font-size: 2em; -fx-text-fill: Blue");
		HBox hbox1 = new HBox(b1,b2,b3);
		VBox vbox1 = new VBox(l1,t1,l2,t2,l3,t3,l4,hbox1);
		HBox hbox2 = new HBox(b4,b5,b6);
		VBox vbox2 = new VBox(l5,t4,l6,t5,l7,t6,l8,hbox2);
		HBox hbox3 = new HBox(l9,l10);
		hbox3.setSpacing(740);

		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Options");
		mOne = new MenuItem("Exit");
		mOne.setOnAction(e->{Platform.exit();});
		mTwo = new MenuItem("Fresh Start");
		mTwo.setOnAction(e->{winningsp1=0;winningsp2=0;sceneCount++;
			sceneMap.put("scene"+sceneCount, originalScene(primaryStage));
			primaryStage.setScene(sceneMap.get("scene"+sceneCount));});
		mThree = new MenuItem("New Look");
		mThree.setOnAction(e->{sceneMap.put("newLook", newLookScene(primaryStage));
			primaryStage.setScene(sceneMap.get("newLook"));});
		menu.getItems().addAll(mOne,mTwo, mThree);
		menuBar.getMenus().add(menu);
		menu.setStyle("-fx-border-width: 2px; -fx-font-size: 2em; -fx; -fx-border-color: black; -fx-background-color: gray");

		l11 = new Label("         Game Start!\n" +
				"   Place Ante Bet: 5-25\n" +
				"  Optional PP Bet: 5-25");
		l11.setStyle("-fx-border-color: Black; -fx-border-width: 4px; -fx-font-size: 3em; -fx-text-fill: Red");
		HBox hbox4 = new HBox(menuBar,l11);
		hbox4.setSpacing(270);
		borderPane = new BorderPane();
		borderPane.setPrefSize(1200,700);
		Image image = new Image(getClass().getResourceAsStream("PokerBackground.jpg"));
		Image cardBack = new Image("CardBack.png");
		ImageView cardBackView = new ImageView(cardBack);
		cardBackView.setFitHeight(200);
		cardBackView.setFitWidth(150);
		BackgroundSize bSize = new BackgroundSize(700, 700, true, true, true, true);
		borderPane.setBackground(new Background(new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		borderPane.setLeft(vbox1);
		borderPane.setRight(vbox2);
		borderPane.setBottom(hbox3);
		borderPane.setTop(hbox4);

		Label l13 = new Label("     ");
		hboxDealer = new HBox();
		hboxDealer.setSpacing(5);
		Label l14 = new Label("     ");
		hbox5 = new HBox(l13,cardBackView, l14,hboxDealer);
		vbox3 = new VBox(hbox5,hbox);
		hbox.setSpacing(5);
		borderPane.setCenter(vbox3);

		l9.setAlignment(Pos.BOTTOM_LEFT);
		l10.setAlignment(Pos.BOTTOM_RIGHT);
		vbox1.setAlignment(Pos.CENTER_LEFT);
		vbox2.setAlignment(Pos.CENTER_RIGHT);

		return new Scene(new VBox(borderPane), 1200,700);
	}
	private String suit(Card c) {
		if(c.getSuit() == 'C') {
			return "clubs.png";
		}
		if(c.getSuit() == 'D') {
			return "diamonds.png";
		}
		if(c.getSuit() == 'S') {
			return "spades.png";
		}
		if(c.getSuit() == 'H') {
			return "hearts.png";
		}
		else {
			return "";
		}
	}
	public void dealCards() {
		for(int i = 0; i < 3; i++) {
			String filename = playerOne.getHand().get(i).getValue() + "_of_" + suit(playerOne.getHand().get(i));
			Image card = new Image(filename);
			ImageView cardView = new ImageView(card);
			cardView.setFitHeight(160);
			cardView.setFitWidth(110);
			//pause.setOnFinished(e->{hbox.getChildren().add(cardView);});
			hbox.getChildren().add(cardView);
		}
		Label l = new Label("        ");
		hbox.getChildren().add(l);
		for(int i = 0; i < 3; i++) {
			String filename = playerTwo.getHand().get(i).getValue() + "_of_" + suit(playerTwo.getHand().get(i));
			Image card = new Image(filename);
			ImageView cardView = new ImageView(card);
			cardView.setFitHeight(160);
			cardView.setFitWidth(110);
			hbox.getChildren().add(cardView);
		}
	}

	public void DealerCards() {
		hboxDealer.getChildren().clear();
		for(int i = 0; i < 3; i++) {
			String filename = theDealer.getDealersHand().get(i).getValue() + "_of_" + suit(theDealer.getDealersHand().get(i));
			Image card = new Image(filename);
			ImageView cardView = new ImageView(card);
			cardView.setFitHeight(160);
			cardView.setFitWidth(110);
			hboxDealer.getChildren().add(cardView);
		}
	}

	public void restart(Stage primaryStage) {
		pause2.setOnFinished(e->{sceneCount++;
			sceneMap.put("scene"+sceneCount, originalScene(primaryStage));
			primaryStage.setScene(sceneMap.get("scene"+sceneCount));
		});
		pause2.play();
	}

}
