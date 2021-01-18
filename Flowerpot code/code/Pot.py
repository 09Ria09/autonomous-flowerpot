import time


class Pot:

    min_water = 0
    max_water = 0

    water_pump_port = 0
    water_sensor_port = 0

    time_last_watering = 0.0

    def __init__(self, min_water, max_water, water_pump_port, water_sensor_port):
        self.min_water = min_water
        self.max_water = max_water
        self.water_pump_port = water_pump_port
        self.water_sensor_port = water_sensor_port

    def run_iteration(self):

        curr_time = time.time()

        if curr_time - self.time_last_watering < 30:
            return

        water_level = self.get_soil_humidity()

        if water_level < self.min_water:
            self.pump_water(self.max_water - water_level)
            self.time_last_watering = time.time()

    def get_soil_humidity(self):
        return 100

    def pump_water(self, x):
        return
