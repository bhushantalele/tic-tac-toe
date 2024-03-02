import Controller.GameController;
import models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("WELCOME TO TicTacToeGame");

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the Dimension of the game");
        int dimension = scanner.nextInt();

        System.out.println("Number of players");
        int noOfPlayers = scanner.nextInt();

        List<Player> players = new LinkedList<>();
        System.out.println("Will there be any bot");
        String isbot = scanner.next();

        if (isbot.equals("y")) {
            noOfPlayers = noOfPlayers - 1;

            System.out.println("Enter the name of Bot : ");
            String name = scanner.next();

            System.out.println("Enter the symbol of Bot : ");
            String botSymbol = scanner.next();

            System.out.println("Enter Bot Difficulty Level " +
                    ": 1-EASY 2-MEDIUM 3-HARD ");
            int difficultyLevel = scanner.nextInt();

            players.add(new Bot(botSymbol.charAt(0), name,
                    BotDifficultyLevel.EASY));
        }

        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter the name of Player : " + (i + 1));
            String name = scanner.next();

            System.out.println("Enter the symbol for Player : " + (i + 1));
            String symbol = scanner.next();

            Player player = new Player(symbol.charAt(0), name, PlayerType.HUMAN);
            players.add(player);
        }

//        Game game = Game.getBuilder()
//                .setDimension(dimension)
//                .setPlayers(players)
//                .build();

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game) == GameStatus.IN_PROGRESS) {
            // TODO Play Game

            System.out.println("Current Board: ");
            gameController.displayBoard(game);

            gameController.executeNextMove(game);
        }
        if (gameController.getGameStatus(game) == GameStatus.DRAW) {
            System.out.println("Game has Draw");
        } else {
            System.out.println("Game has won by : " + gameController.getWinnerName(game));
        }
    }
}
