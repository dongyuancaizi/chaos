function phone() {
    var phone = wx.getStorageSync('phone')
    if (!phone.match(/^1\d{10}/)) {
        wx.reLaunch({
            url: '../authorize/authorize',
        })
    }
}

module.exports = {
    phone: phone
}
