import time
import RPi.GPIO as gpio

watering_constant = 0.5


class Pot:

    min_water = 0
    max_water = 0

    pump_pow = 0
    pump_in1 = 0
    pump_in2 = 0

    time_last_watering = 0.0

    def __init__(self, min_water, max_water, pump_pow, pump_in1, pump_in2):
        self.min_water = min_water
        self.max_water = max_water
        self.pump_pow = pump_pow
        self.pump_in1 = pump_in1
        self.pump_in2 = pump_in2

        # pump pins
        gpio.setup(self.pump_pow, gpio.OUT)
        gpio.setup(self.pump_in1, gpio.OUT)
        gpio.setup(self.pump_in2, gpio.OUT)

        gpio.output(self.pump_pow, gpio.LOW)
        gpio.output(self.pump_in1, gpio.LOW)
        gpio.output(self.pump_in2, gpio.LOW)

    def run_iteration(self):

        curr_time = time.time()

        if curr_time - self.time_last_watering < 30:
            return

        water_level = self.get_soil_humidity()

        if water_level < self.min_water:
            self.pump_water(self.max_water - water_level)
            self.time_last_watering = time.time()

    def get_soil_humidity(self):
        return 1

    def pump_water(self, x):


        print("ai intrat prostule")

        water_time = x * watering_constant

        # run clockwise
        gpio.output(self.pump_in1, gpio.HIGH)
        gpio.output(self.pump_in2, gpio.LOW)

        # set motor speed
        gpio.output(self.pump_pow, gpio.HIGH)

        # disable standby
        gpio.output(13, gpio.HIGH)

        time.sleep(water_time)

        # reset pins
        gpio.output(self.pump_in1, gpio.LOW)
        gpio.output(self.pump_in2, gpio.LOW)
        gpio.output(self.pump_pow, gpio.LOW)

        # reset standby
        gpio.output(13, gpio.LOW)
