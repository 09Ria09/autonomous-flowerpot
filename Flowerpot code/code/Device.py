import Pot
import Screen
import Rotary
import RPi.GPIO as gpio


class Device:

    def __init__(self, rotary_clk, rotary_dt, rotary_press):
        gpio.setmode(gpio.BOARD)

        self.pots = []

        self.page = -1
        self.row_selected = 2

        self.screen = Screen.lcd()

        self.rotary = Rotary.Rotary(rotary_clk, rotary_dt, rotary_press, self.on_turn, self.on_press)

    def add_pot(self, min_water, max_water, pump_pow, pump_in1, pump_in2):
        self.pots.append(Pot.Pot(min_water, max_water, pump_pow, pump_in1, pump_in2))

    def run_iteration(self):
        for pot in self.pots:
            pot.run_iteration()

        self.rotary.check()

    def draw_home(self):
        self.screen.lcd_clear()

        self.screen.lcd_display_string("The device has 100% water", 1)

        for i in range(1, 4):
            s = "Plant " + str(i)

            if i + 1 == self.row_selected:
                s = s + " <"

            self.screen.lcd_display_string(s, i+1)

    def on_turn(self):
        return

    def on_press(self):
        return
