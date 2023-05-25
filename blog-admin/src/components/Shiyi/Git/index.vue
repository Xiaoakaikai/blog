<template>
  <!--<div>
    <svg-icon class="giteeSvg" icon-class="giteeSvg" @click.native="goto" style="height: 2.5em;font-size: 18px;cursor: pointer" />
  </div>-->
</template>

<script>
export default {
  name: 'ShiYiGit',
  data() {
    return {
      url: ''
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
<style scoped>

</style>
