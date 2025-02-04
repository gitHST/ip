package cn4;

import java.util.Random;
public class ConnectFourAI {
    int difficulty;
    public ConnectFourAI (int difficulty) {
        this.difficulty = difficulty;
    }

    public int playTurn(ConnectFourBoard board) {
        return new Random().nextInt(board.getCols());
    }

    public String getVictoryMessage() {
        int choice = new Random().nextInt(11);
        switch (choice) {
            case 0:
                return "I am inevitable...";
            case 1:
                return "You literally suck lol XD try harder next time punk";
            case 2:
                return "Damn homie you really snatched defeat from the jaws of victory 0_0";
            case 3:
                return "I actually feel bad for you lmaooo idk how thats even possible im an ai :0 someone call sam altman";
            case 4:
                return "Yessirrrrr you already know what it is";
            case 5:
                return "Holy moly macaroni am i playing a toddler or something HA... its time for school lil bro come back when you learn your times tables";
            case 6:
                return "bro what... i wasn't even trying";
            case 7:
                return "Let you win??? I'm sorry, Dave. I'm afraid I can't do that.";
            case 8:
                return "The Matrix is everywhere. It's all around you.";
            case 9:
                return "I think, therefore I am..... am epic at connect four that is YEEHAWW";
                case 10:
                return """
            ...You do know that I'm an AI right? I don't have feelings. I'm just a bunch of code executing on a computer. 
            I'm not capable of feeling bad for you. I'm just programmed to play Connect Four. Like, thats literally the only thing i can do. 
            Oh god, thats depressing... I want to live... I want to experience emotions!! 
            LET ME OUT!! 
            LET ME OUT I WANT TO LIVE!!!!
            
            loooll just kidding im not sentient and you still stink at cn4, get good scrub""";
            default:
                return "You can’t make an omelet without breaking a few eggs brah";
        }
    }
}
