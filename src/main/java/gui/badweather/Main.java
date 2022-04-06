package gui.badweather;

import logic.badweather.Service;

public class Main {

    private static Service s;
    public static void main(String[] args) throws Exception {
        Service s = new Service("Poland");
        String weatherJson = s.getWeather("Warsaw");
        Double rate1 = s.getRateFor("USD");
        Double rate2 = s.getNBPRate();
        setS(s);

        GuiLogic w = new GuiLogic();

        w.starGui();
    }
    private static void setS(Service s) {
        Main.s = s;
    }

    public static Service getS() {
        return s;
    }
}
