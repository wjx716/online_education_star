<template>
  <div class="app-container">
    <el-input
      v-model="filterText"
      placeholder="Filter keyword"
      style="margin-bottom:30px;"
    />

    <el-tree
      ref="tree"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />
  </div>
</template>

<script>
import edu_subject_api from '@/api/edu_subject'

export default {
  data() {
    return {
      filterText: '',
      data: [],
      defaultProps: {
        children: 'child',
        label: 'title'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
      this.getAllSubjectTree()
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.title.indexOf(value) !== -1
    },
    getAllSubjectTree() {
      edu_subject_api.getAllSubjectTree().then(response => {
        this.data = response.data.allSubjectTree
      })
    }
  }
}
</script>
