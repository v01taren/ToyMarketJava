import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyLottery {
    private List<Toy> toyList;
    private List<Toy> availableToys;
    private Random random;

    public ToyLottery(List<Toy> toys) {
        toyList = new ArrayList<>(toys);
        availableToys = new ArrayList<>(toyList);
        random = new Random();
    }

    public Toy getToy() {
        if (availableToys.isEmpty()) {
            availableToys.addAll(toyList);
        }

        int randomIndex = random.nextInt(availableToys.size());
        return availableToys.remove(randomIndex);
    }

    public static void main(String[] args) {
        List<Toy> toys = new ArrayList<>();

        toys.add(new Toy("1", "Винни-Пух", 0.33));
        toys.add(new Toy("2", "Набор ЛЕГО", 0.33));
        toys.add(new Toy("3", "Машина на радио-управлении", 0.34));

        ToyLottery toyLottery = new ToyLottery(toys);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("wintoys.txt", false))) {
            for (int i = 0; i < 10; i++) {
                Toy wonToy = toyLottery.getToy();
                if (wonToy != null) {
                    String result = "Вы выиграли: " + wonToy.getName();
                    System.out.println(result);
                    writer.write(result);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}