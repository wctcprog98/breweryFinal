<template>
  <div>
    <p>{{ event.eventName }}</p>
    <p>{{ formatTime(event.eventTime) }}</p>
    <p>{{ getBreweryName(event.breweryId) }} {{ breweryName }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      breweryName: "",
    };
  },
  props: {
    event: Object,
  },
  methods: {
    formatTime(time) {
      let hour = time.substring(0, 2);
      const min = time.substring(2, 5);
      let period = " AM";

      if (hour == 12) {
        period = " PM";
      } else if (hour == 0) {
        hour = 12;
      } else if (hour > 12) {
        hour = hour % 12;
        period = " PM";
      }

      return hour + min + period;
    },
    getBreweryName(breweryId) {
      let breweries = this.$store.state.breweries;

      breweries.forEach((brewery) => {
        if (brewery.id == breweryId) {
          this.breweryName = brewery.breweryName;
        }
      });
    },
  },
};
</script>

<style>

.event-date{
  padding: 1%;
  margin-left: 4%;
}

.event-time{
  padding: 1%;
  margin-left: 4%;
}

.event-name-event{
  color: black;
  font-size: 34px;

}

.event-name-event:hover{
  text-decoration:underline;
}


</style>