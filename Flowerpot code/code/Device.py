import Pot
import Screen
import RPi.GPIO as gpio


class Device:

    page = -1
    row_selected = 2
    screen = Screen.lcd()

    pots = []

    def __init__(self):
        gpio.setmode(gpio.BOARD)

    def add_pot(self, min_water, max_water, pump_pow, pump_in1, pump_in2):
        self.pots.append(Pot.Pot(min_water, max_water, pump_pow, pump_in1, pump_in2))

    def run_iteration(self):
        for pot in self.pots:
            pot.run_iteration()

    def draw_home(self):
        self.screen.lcd_clear()

        self.screen.lcd_display_string("The device has 100% water", 1)

        for i in range(1, 4):
            s = "Plant " + str(i)

            if i + 1 == self.row_selected:
                s = s + " <"

            self.screen.lcd_display_string(s, i+1)
