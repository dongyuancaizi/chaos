function alert(txt) {
    wx.showToast({
        title: txt,
        image: '/img/21.png'
    })
}

module.exports = {
    alert: alert
}
