import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {
    list() {
        return http.get('/breweries')
    },
    create(brewery) {
        return http.post('/breweries', brewery)
    },

    get(id) {
        return http.get(`/breweries/${id}`)
    },

    update(brewery, breweryId) {
        return http.put(`/breweries/${breweryId}`, brewery)
    },

    delete(id) {
        return http.delete(`/breweries/${id}`)
    }
}