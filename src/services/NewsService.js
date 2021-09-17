import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    listByBreweryId(id) {
        return http.get(`/breweries/${id}/news`)
    },
    findById(id) {
        return http.get(`/news/${id}`)
    },
    create(news) {
        http.post(`/news`, news)
    },
    update(id, news) {
        http.put(`/news/${id}`, news)
    },
    delete(id) {
        http.delete(`/news/${id}`)
    }

}