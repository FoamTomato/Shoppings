module.exports = app => {
    const express = require('express')

    const jwt = require('jsonwebtoken')
    const assert = require('http-assert')
    const oalur = require('../../models/oalur')
    const contarct = require('../../models/Contarct')
    const qr = require('../../models/Qr')
    const AdminUser = require('../../models/AdminUser')
    const router = express.Router({
        mergeParams: true //表示合并参数
    })

    //登录授权中间键
    const authMiddleware = require('../../middleware/auth')

    //资源中间键
    const resourceMiddleware = require('../../middleware/resource')

    //const Category = require('../../models/Category')
    //创建订单
    router.post("/", async (req, res) => {
        const model = await req.Model.create(req.body)
        res.send(model)
    })
    //查找订单  限制10条
    router.get("/", async (req, res) => {
        //进行名称大小写转换
        const queryOptions = {}
        if (req.Model.modelName === "Category") {
            queryOptions.populate = "parent"
        }
        const items = await req.Model.find().setOptions(queryOptions).limit(10)
        res.send(items)
    })


    //获取总长
    router.get("/size/", async (req, res) => {
        const sizes = await req.Model.find().count()
        res.send(sizes)
    })
    
    app.post('/admin/api/qrById', async (req, res) => {
        const sizes = await qr.find({_id:req.body.id})
        res.send(sizes)
    })
    app.post('/admin/api/oalur', authMiddleware(), async (req, res) => {
        //分页查询

        const items = await oalur.find({}).skip((req.body.pageNum - 1) * req.body.pageSize).limit(req.body.pageSize).sort({ "saleNum": -1 })
        //获取总长
        //const sizes = await oalur.find().count()
        // let datas={}
        // datas["items"]=items
        // datas["sizes"]=1000
        res.send(items)
    })
    app.post('/admin/api/qrs', authMiddleware(), async (req, res) => {
        //分页查询
        let data={}
        //判断是否值为空
        if (req.body.contents != "") {
            let content=req.body.contents.split(/[,_，|/]/)
            //条件查询  或
            data["$or"] = [{ NameOfNotaryOffice: {$in:content} },
            { NotaryCategory: {$in:content} },
            { NameOfNotary: {$in:content} },
            { CaseNumber: {$in:content} },
            { NotarialNumber: {$in:content} },
            { NotarizedEvent: {$in:content} },
            { Translation: {$in:content} },
            { Proposer: {$in:content} },
            { UseTo: {$in:content} }]
        }
        const items = await qr.find(data)
            .skip((req.body.pageNum - 1) * req.body.pageSize)
            .limit(req.body.pageSize)//.sort({ "saleNum": -1 })

        res.send(items)
    })
    app.post('/admin/api/contarct', authMiddleware(), async (req, res) => {
        //分页查询
        let data = {}
        data["states"] = '1'
        //判断是否值为空
        if (req.body.contents != "") {
            let content=req.body.contents.split(/[,_，|/]/)
            //条件查询  或
            data["$or"] = [{ idCard: {$in:content} },
            { company: {$in:content} },
            { erpAccount: {$in:content} },
            { name: {$in:content} },
            { phone: {$in:content} },
            { qqEmail: {$in:content} },
            { qqNumber: {$in:content} },
            { weChat: {$in:content} }]
        }
        const items = await contarct.find(data)
            .skip((req.body.pageNum - 1) * req.body.pageSize)
            .limit(req.body.pageSize).sort({ "saleNum": -1 })

        res.send(items)
    })
    //获取总长
    app.get('/admin/api/oalur', authMiddleware(), async (req, res) => {
        //获取总长
        const sizes = await oalur.find().count()
        res.send(sizes)
    })
    

    //获取总长
    app.get('/admin/api/contarct', authMiddleware(), async (req, res) => {
        //获取总长
        const sizes = await contarct.find({ 'states': '1' }).count()
        res.send(sizes)
    })

     //获取二维码
     app.get('/admin/api/qr', authMiddleware(), async (req, res) => {
        //获取总长
        //const sizes = await contarct.find({ 'states': '1' }).count()
        res.send("123")
    })
    //获取单独订单
    router.get("/:id", async (req, res) => {
        const items = await req.Model.findById(req.params.id)
        res.send(items)
    })
    //修改单独订单
    router.put("/:id", async (req, res) => {
        const items = await req.Model.findByIdAndUpdate(req.params.id, req.body)
        res.send(items)
    })
    //删除订单
    router.delete("/:id", async (req, res) => {
        //await req.Model.findByIdAndDelete(req.params.id, req.body)
        await req.Model.findByIdAndDelete(req.params.id, req.body)
        res.send({
            success: true
        })
    })
    app.use('/admin/api/rest/:resource', authMiddleware(), resourceMiddleware(), router)


    //下载图片
    const multer = require('multer')
    //传入的路径 __dirname是绝对地址
    const upload = multer({ dest: __dirname + '/../../uploads' })
    app.use('/admin/api/upload', authMiddleware(), upload.single('file'), async (req, res) => {
        const file = req.file
        file.url = `http://192.168.1.181:3000/uploads/${file.filename}`
        res.send(file)
    })

    //登录
    app.post('/admin/api/login', async (req, res) => {
        const { username, password } = req.body
        //1.根据用户名找用户
        const user = await AdminUser.findOne({ username }).select('+password')//查找一条
        assert(user, 422, '用户不存在')

        //2.校验密码
        const isValid = require('bcryptjs').compareSync(password, user.password)//对比明文和密文

        assert(isValid, 422, '验证错误')
        // if (!isValid) {
        //     return res.status(422).send({
        //         message: '验证错误'
        //     })
        // }
        //3.返回token
        const token = jwt.sign({ id: user._id }, app.get('secret'))
        const usernames = user.username
        res.send({ "token": token, "username": usernames })
    })
    //错误处理函数
    app.use(async (err, req, res, next) => {
        res.status(err.statusCode || 500).send({
            message: err.message
        })
    })
}