import React, { Component } from 'react';



export default class PageDefinitionSearchTable extends Component {


    handleProcessIdChange(event){
        this.props.handleProcessIdChange(event.target.value);
    }

    handleGroupIdChange(event){
        this.props.handleGroupIdChange(event.target.value);
    }

    handleArtifactIdChange(event){
        this.props.handleArtifactIdChange(event.target.value);
    }

    handleVersionChange(event){
        this.props.handleVersionChange(event.target.value);
    }


  render() {

    return (
      <div>


                  <div className="form-group required">
                      <label className="col-sm-2 control-label">{this.props.tableHeader} processId</label>
                      <div className="col-sm-10">
                          <input className="form-control" type="text" name="processId" value={this.props.processId} onChange={ (e) => this.handleProcessIdChange(e) }/>
                    </div>
                  </div>

                  <div className="form-group required">
                      <label className="col-sm-2 control-label">{this.props.tableHeader} Group ID</label>
                      <div className="col-sm-10">
                          <input className="form-control" type="text" name="groupId" value={this.props.groupId} onChange={ (e) => this.handleGroupIdChange(e) }/>
                    </div>
                  </div>

                  <div className="form-group required">
                      <label className="col-sm-2 control-label">{this.props.tableHeader} Artifact ID</label>
                      <div className="col-sm-10">
                          <input className="form-control" type="text" name="artifactId" value={this.props.artifactId} onChange={ (e) => this.handleArtifactIdChange(e) }/>
                    </div>
                  </div>

                  <div className="form-group required">
                      <label className="col-sm-2 control-label">{this.props.tableHeader} Version</label>
                      <div className="col-sm-10">
                          <input className="form-control" type="text" name="version" value={this.props.version} onChange={ (e) => this.handleVersionChange(e) }/>
                    </div>
                  </div>

    </div>



    );
  }
}
