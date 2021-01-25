import time
import RPi.GPIO as gpio


class Rotary:

    def __init__(self, clk, dt, press, on_turn, on_press):
        self.clk = clk
        self.dt = dt
        self.press = press

        gpio.setup(self.clk, gpio.IN, pull_up_down=gpio.PUD_DOWN)
        gpio.setup(self.dt, gpio.IN, pull_up_down=gpio.PUD_DOWN)
        gpio.setup(self.press, gpio.IN, pull_up_down=gpio.PUD_DOWN)

        self.on_turn = on_turn
        self.on_press = on_press

        self.counter = 0
        self.clk_last_state = gpio.input(self.clk)

        self.last_press = 0

    def check(self):
        clk_state = gpio.input(self.clk)
        dt_state = gpio.input(self.dt)

        if clk_state != self.clk_last_state:
            if clk_state == dt_state:
                self.on_turn(1)
            else:
                self.on_turn(-1)

        self.clk_last_state = clk_state
        time.sleep(0.01)

        curr_time = time.time()

        if curr_time - self.last_press > 0.1:

            press_state = gpio.input(self.press)
            self.last_press = curr_time

            if press_state:
                self.on_press()