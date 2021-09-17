<template>
  <div class="events-list">
   <h1 class="event-label">Upcoming Events!</h1>
<div  >
    <events-summary 
      class="event-item" 
      v-for="event in allEvents"
      v-bind:key="event.id"
      v-bind:event="event"
    />
  </div>
  </div>
</template>

<script>
import EventService from "@/services/EventService";
import EventsSummary from "./EventsSummary.vue";
export default {
  components: { EventsSummary },
  data() {
    return {
      allEvents: [],
    };
  },
  created() {
    EventService.listUpComing().then((response) => {
      this.allEvents = response.data;
    });
  },
};
</script>

<style>
.event-item {
  border: 1px solid white;
}
.event-item {
  border: 2px solid black;
  border-radius: 15px;
  padding: 3px;
  margin: 4%;
  box-shadow: 3px 2px 14px rgba(white, 0.2);
  transition: all 200ms ease-in-out;
  background-color: white;
  
  /* beer card */
  opacity: 50%;
  color: rgb(8, 8, 4);
  filter: blur(0.7px);
  /* font-size: 20px; */
  /* test */

}

.event-item:hover {
  transform: translateY(-1rem);
  box-shadow: 3px 2px 18px rgba(255.255.255, 0.4);
  color: rgb(175, 150, 7);
  font-weight: 900;
  font-size: 30px;
  text-decoration: underline;
  border-bottom: 6px solid black;
  border-right: 6px solid black;
  border-top: 0px;
  border-left: 0px;
  filter: blur(0px);
  opacity: 90%;
}


.event-label{
  position:absolute;
  top: 1099px;
  background-color: rgb(92, 76, 43);
   border-radius: 20px;
   color:white;
  font-size: 55px;
}
</style>