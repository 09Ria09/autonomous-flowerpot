import time
import Pot
import Screen
import Rotary
import RPi.GPIO as gpio


class Device:

    def __init__(self, rotary_clk, rotary_dt, rotary_press):
        gpio.setmode(gpio.BOARD)

        self.pots = []

        self.last_page = 0
        self.page = -1
        self.row_selected = 2

        self.screen = Screen.lcd()

        self.rotary = Rotary.Rotary(rotary_clk, rotary_dt, rotary_press, self.on_turn, self.on_press)

        # self.draw_home()
        # time.sleep(5000)

    def add_pot(self, min_water, max_water, pump_pow, pump_in1, pump_in2):
        self.pots.append(Pot.Pot(min_water, max_water, pump_pow, pump_in1, pump_in2))

    def run_iteration(self):
        print("got to run_iteration")
        for pot in self.pots:
            pot.run_iteration()

        self.rotary.check()
        self.draw()

    def draw(self):

        """"
        page
        -1 home
        0 plant 0
        1 plant 1
        2 plant 2
        """

        if self.page == self.last_page:
            return

        self.last_page = self.page

        print("got to main draw")
        if self.page == -1:
            self.draw_home()
        else:
            self.draw_plant()

    def draw_home(self):

        print("got to draw_home")
        self.screen.lcd_clear()

        self.screen.lcd_display_string("The device has 100% water", 1)

        for i in range(1, 4):
            s = "Plant " + str(i)

            if i + 1 == self.row_selected:
                s = s + " <"

            self.screen.lcd_display_string(s, i+1)

    def draw_plant(self):
        self.screen.lcd_clear()

        for i in range(1, 5):

            s = ""
            if i == 1:
                s = "Stats"
            if i == 2:
                s = "Change name"
            if i == 3:
                s = "Watering mode"
            if i == 4:
                s = "Back"

            if i == self.row_selected:
                s = s + " <"

            self.screen.lcd_display_string(s, i)

    def on_turn(self, x):

        self.last_page = -2
        self.row_selected = self.row_selected + x

        if self.row_selected == 0:
            self.row_selected = 4

        if self.row_selected == 5:
            self.row_selected = 1

    def on_press(self):
        if self.page == -1:

            if self.row_selected == 1:
                return
            else:
                self.page = self.row_selected - 2

        else:
            if self.row_selected == 4:
                self.page = -1
