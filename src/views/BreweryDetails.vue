<template>
  <div class="menu-container">
    <Header />
    <brewery-contact v-bind:brewery="this.thisBrewery" />
    <button
      class="update-form2"
      v-show="$store.state.user.id == thisBrewery.brewerId"
      v-on:click="showUpdateForm()"
    >
      Update
    </button>
    <update-brewery-form
      v-if="this.$store.state.showUpdateBreweryForm"
      v-bind:breweryNumber="breweryId"
    />
    <div class="menu">
      <brewery-menu
        v-bind:breweryNumber="breweryId"
        v-bind:brewerId="thisBrewery.brewerId"
      />
    </div>
  </div>
</template>

<script>
import BreweryContact from "../components/BreweryContact.vue";
import UpdateBreweryForm from "../components/UpdateBreweryForm.vue";
import BreweryService from "@/services/BreweryService";
import Header from "../components/Header.vue";
import BreweryMenu from "../components/BreweryMenu.vue";
export default {
  data() {
    return {
      breweryId: parseInt(this.$route.params.id),
      thisBrewery: {},
    };
  },
  components: {
    UpdateBreweryForm,
    BreweryContact,
    Header,
    BreweryMenu,
  },
  methods: {
    showUpdateForm() {
      this.$store.commit("TOGGLE_UPDATE_BREWERY");
    },
  },
  created() {
    BreweryService.get(this.breweryId).then((response) => {
      if (response.status === 200) {
        this.thisBrewery = response.data;
      }
    });
  },
};
</script>

<style>
.update-form2 {
  border: 2px solid black;
  border-radius: 15px;
  background-color: rgb(247, 221, 104);
  font-weight: bolder;
  font-size: 17px;

  padding-left: 1%;
  border-bottom: 6px solid black;
  border-right: 6px solid black;
  border-top: 0px;
  border-left: 0px;
  filter: blur(0px);
  margin-left: 70px;
  width: 164px;
  height: 28px;
}

.update-form2:hover {
  background-color: rgb(255, 212, 19);
}
</style>