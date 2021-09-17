<template>
  <div class>
    <h1>Details</h1>
    <button
      class="delete-beer"
      v-show="$store.state.user.id == brewerId"
      v-on:click="deleteBeer()"
    >
      Delete
    </button>
    <span>Description:</span>
    <div class="beer-des">{{ beer.beerDescription }}</div>
    <new-review-form v-bind:beerId="beer.id" />
    <review-list v-bind:reviews="reviews" />
  </div>
</template>

<script>
import beerService from "@/services/BeerService";
import ReviewService from "@/services/ReviewService";
import ReviewList from "./ReviewList.vue";
import NewReviewForm from "./NewReviewForm.vue";

export default {
  components: {
    ReviewList,
    NewReviewForm,
  },
  props: {
    beer: Object,
    brewerId: Number,
  },
  data() {
    return {
      reviews: [],
    };
  },
  created() {
    ReviewService.listByBeerId(this.beer.id).then((response) => {
      if (response.status == 200) {
        this.reviews = response.data;
      }
    });
  },
  methods: {
    deleteBeer() {
      if (confirm("Are you sure you want to permanently delete this beer?"))
        beerService.delete(this.beer.id).then((response) => {
          if (response.status == 200) {
            this.$router.go();
          }
        });
    },
  },
};
</script>

<style>
.delete-beer{
  float: right;
  background-color: red;
  opacity: 80%;
}
</style>