package gui.badweather;

import logic.badweather.Service;

public class Main {
    public static void main(String[] args) throws Exception {
        Service s = new Service("Poland");
        String weatherJson = s.getWeather("Warsaw");
        Double rate1 = s.getRateFor("USD");
        Double rate2 = s.getNBPRate();

        GuiLogic w = new GuiLogic();

        w.starGui();
    }
}
