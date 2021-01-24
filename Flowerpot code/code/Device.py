import Pot
import RPi.GPIO as gpio


class Device:

    pots = []

    def __init__(self):
        gpio.cleanup()
        gpio.setmode(gpio.BOARD)

    def add_pot(self, min_water, max_water, pump_pow, pump_in1, pump_in2):
        self.pots.append(Pot.Pot(min_water, max_water, pump_pow, pump_in1, pump_in2))

    def run_iteration(self):
        for pot in self.pots:
            pot.run_iteration()
