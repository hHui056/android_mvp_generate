package com.hh.baselibrary.widget.tree

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.hh.baselibrary.R
import java.util.HashMap

/**
 *
 * @param treeType 树状类型
 *
 */
class SimpleTreeAdapter<T>(mTree: ListView, context: Context, var datas: List<T>, private val defaultExpandLevel: Int, var treeType: TreeType) : TreeListViewAdapter<T>(mTree, true, context, datas, defaultExpandLevel) {
    private val tag = this.javaClass.simpleName

    //存checkbox选中的值
    private val selectMap = HashMap<String, Node>()

    private var editListener: OnEditListener? = null

    private var dataList: List<Node>

    /**
     * 设置是否只有根节点可选择
     */
    var onlyFirstLevelCanChoose = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    /**
     * 是否显示checkbox
     */
    private var showCheckBox = false
        get() {
            return treeType == TreeType.Radio || treeType == TreeType.Multi
        }

    /**
     * 是否显示add图片
     */
    private var showAddImage = false
        get() {
            return treeType == TreeType.Add
        }

    init {
        dataList = TreeHelper.getSortedNodes(datas, defaultExpandLevel)
        dataList.forEach {
            selectMap[it.id] = it
        }
    }

    override fun getConvertView(node: Node, position: Int, parent: ViewGroup): View {
        return mInflater.inflate(R.layout.tree_item_layout, parent, false)
    }

    override fun fillValue(node: Node, convertView: View) {
        val swipelayout = convertView.findViewById<SwipeLayout>(R.id.swipe_goods_type)
        val icon = convertView.findViewById<ImageView>(R.id.icon)
        val label = convertView.findViewById<TextView>(R.id.label)
        val check = convertView.findViewById<CheckBox>(R.id.id_three_node_check)
        val add = convertView.findViewById<ImageView>(R.id.id_three_node_add)
        val delete = convertView.findViewById<TextView>(R.id.btn_delete)
        if (node.icon == -1) { //叶子节点
            icon.visibility = View.INVISIBLE
        } else {
            icon.visibility = View.VISIBLE
            icon.setImageResource(node.icon)
        }
        if (showCheckBox) {
            val a = selectMap[node.id]
            check.isChecked = a!!.select != "0"
            if (onlyFirstLevelCanChoose) {//隐藏其他节点的checkbox
                check.visibility = if (node.level == 0) View.VISIBLE else View.GONE
            } else {
                check.visibility = View.VISIBLE
            }
        } else {
            check.visibility = View.GONE
        }


        //最多支持编辑两级
        add.visibility = if (showAddImage && node.level < 2) View.VISIBLE else View.GONE
        swipelayout.isSwipeEnabled = treeType == TreeType.Add
        if (treeType == TreeType.Multi || treeType == TreeType.Radio) {
            check.setOnClickListener {
                node.select = if (node.select != "0") "0" else "1"
                if (treeType == TreeType.Radio && node.select == "1") { //选中此-->之前选中的置为未选中
                    val a = selectMap.values.toList().find { it.select == "1" && it.id != node.id }
                    if (a != null) {
                        a.select = "0"
                        selectMap[a.id] = a
                    }
                }
                selectMap[node.id] = node
                notifyDataSetChanged()
            }
        }
        if (showAddImage) {
            add.setOnClickListener { editListener?.onAdd(node) }
            delete.setOnClickListener {
                editListener?.onDelete(node)
                swipelayout.close()
            }
        }
        label.text = node.name
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe_goods_type
    }

    /**
     * 选中所有一级节点
     */
    fun chooseAllFirstLevel() {
        selectMap.filter { it.value.level == 0 }.forEach {
            it.value.select = "1"
        }
        notifyDataSetChanged()
    }

    /**
     *取消选中已经选择的项
     */
    fun cancelChoose() {
        selectMap.filter { it.value.select == "1" }.forEach { it.value.select = "0" }
        notifyDataSetChanged()
    }


    /**
     * 编辑监听
     */
    fun setOnEditListener(listener: OnEditListener) {
        this.editListener = listener
    }

    /**
     * 单选时获取选中的[Node]
     */
    fun getChooseNode(): Node? {
        return try {
            selectMap.values.toList().filter { it.select == "1" }[0]
        } catch (e: Exception) {
            null
        }
    }

    /**
     *多选时获取选中的[Node]
     */
    fun getChooseNodes(): List<Node> {
        return selectMap.values.toList().filter { it.select == "1" }
    }

    /**
     * 选中某一项
     */
    fun choose(id: String?) {
        if (id == null) return
        val a = selectMap[id] ?: return
        a.select = "1"
        selectMap[id] = a
        notifyDataSetChanged()
    }

    /**
     * 多选时选中多项
     */
    fun choose(ids: Array<String?>) {
        ids.forEach {
            if (it != null) {
                val a = selectMap[it]
                if (a != null) {
                    a.select = "1"
                    selectMap[it] = a
                }
            }
        }
        notifyDataSetChanged()
    }

    /**
     * 刷新树
     */
    fun refreshTree(datas: List<T>) {
        this.datas = datas
        dataList = TreeHelper.getSortedNodes(datas, defaultExpandLevel)
        selectMap.clear()
        dataList.forEach {
            selectMap[it.id] = it
        }
        refreshList(datas, defaultExpandLevel)
    }

}