var isLocal = false //是否本地环境
var head = isLocal ? "http://" : "https://";
var host = isLocal ? "127.0.0.1:28083" : "dev.iya101.com/iya-api";

function getData(param, data, cb) {
    wx.request({
        url: head + host + "/" + param,
        data: data,
        method: 'post',
        header: {
            "token": wx.getStorageSync('sessionKey')
        },
        success: function (res) {
            if (res.data.code == "200") {
                return typeof cb == "function" && cb(res.data)
            } else if (res.data.code == "401") {
                //login.login()
            } else {
                wx.showToast({
                    icon: 'none',
                    image: '/img/21.png',
                    title: res.data.msg,
                })
            }
        },
        fail: function (res) {
            wx.showToast({
                icon: 'none',
                image: '/img/21.png',
                title: res.data.msg,
            })
        }
    })
}

module.exports = {
    post: getData
}
