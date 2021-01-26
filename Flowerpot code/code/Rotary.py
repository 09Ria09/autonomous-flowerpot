import time
import RPi.GPIO as gpio


class Rotary:

    er = 2

    def __init__(self, clk, dt, press, on_turn, on_press):
        self.clk = clk
        self.dt = dt
        self.press = press

        gpio.setup(self.clk, gpio.IN, pull_up_down=gpio.PUD_DOWN)
        gpio.setup(self.dt, gpio.IN, pull_up_down=gpio.PUD_DOWN)
        gpio.setup(self.press, gpio.IN, pull_up_down=gpio.PUD_UP)

        self.on_turn = on_turn
        self.on_press = on_press

        self.counter = 0
        self.clk_last_state = gpio.input(self.clk)

        self.last_press = 0

    def check(self):
        clk_state = gpio.input(self.clk)
        dt_state = gpio.input(self.dt)
        press_state = 1 - gpio.input(self.press)

        print(press_state)

        if clk_state != self.clk_last_state:
            if clk_state == dt_state:
                self.counter = self.counter + 1
            else:
                self.counter = self.counter - 1

        self.clk_last_state = clk_state
        time.sleep(0.01)

        if self.counter > self.er:
            self.on_turn(1)
            self.counter = 0

        if self.counter < -self.er:
            self.on_turn(-1)
            self.counter = 0

        curr_time = time.time()

        if curr_time - self.last_press > 0.1 and press_state:
            self.last_press = curr_time
            self.on_press()
