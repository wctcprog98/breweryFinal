import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})
// check to see if this is the path we want everything to happen at. 
export default {
    listByBreweryId(breweryId) {
        return http.get(`/breweries/${breweryId}/beer`)
    },
    listTopRated() {
        return http.get(`/beer`)
    },
    create(beer) {
        return http.post(`/beer`, beer)
    },
    delete(beerId) {
        return http.delete(`/beer/${beerId}`)
    },
    update(beerId, beer) {
        return http.put(`/beer/${beerId}`, beer)
    },
    get(beerId) {
        return http.get(`/beer/${beerId}`)
    }
}